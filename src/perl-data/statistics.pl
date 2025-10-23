#Modules used
use strict;
use warnings;
use LWP::UserAgent;
use HTTP::Request::Common;
use XML::LibXML;
use Data::Dumper;

my $setting = $ARGV[0];

if (not defined $setting) {
  die "Need setting\n";
}

my $message = <<'XML';
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
				  xmlns:gs="match.pltracker.com">
   <soapenv:Header/>
   <soapenv:Body>
      <gs:getAllMatchesRequest>
      </gs:getAllMatchesRequest>
   </soapenv:Body>
</soapenv:Envelope>
XML

my $webpage="http://localhost:8080/ws";
# Create a user agent
my $ua = LWP::UserAgent->new; 
my $response = $ua->post($webpage, Content_Type => 'text/xml', Content => $message);
if ($response->is_success) {
    my $xml_string = $response->decoded_content;

    my $dom = XML::LibXML->load_xml(string => $xml_string);

    # Create an XPath context and register namespaces used in the document
    my $xpc = XML::LibXML::XPathContext->new($dom);
    # register the prefixes (use the actual URI from your SOAP response)
    $xpc->registerNs('ns2', 'match.pltracker.com');
    $xpc->registerNs('soap', 'http://schemas.xmlsoap.org/soap/envelope/');

    my @match_nodes = $dom->findnodes('//*[local-name()="matchDetails"]');

    my @matches;

    foreach my $node (@match_nodes) {
        my %match;
        for my $child ($node->nonBlankChildNodes) {
            (my $key = $child->nodeName) =~ s/^ns2://;  # remove namespace
            $match{$key} = $child->textContent;
        }
        push @matches, \%match;
    }

   
    # Hash to count wins per team
    my (%wins, %matches_played);

    for my $match (@matches) {
        my $home_team = $match->{homeTeam};
        my $away_team = $match->{awayTeam};
        my $home_score = $match->{home_score};
        my $away_score = $match->{away_score};

        # Skip invalid/malformed scores (non-numeric)
        next unless defined $home_score && defined $away_score;
        next unless $home_score =~ /^[0-9]{1,2}$/ && $away_score =~ /^[0-9]{1,2}$/;

        # Count total matches played
        $matches_played{$home_team}++;
        $matches_played{$away_team}++;

        if ($home_score > $away_score) {
            $wins{$home_team}++;
        } elsif ($away_score > $home_score) {
            $wins{$away_team}++;
        }
        # Draws don't count as a win
    }
    my %winrate;
    for my $team (keys %matches_played) {
        my $wins = $wins{$team} // 0;           # default to 0 if no wins
        my $total = $matches_played{$team};
        $winrate{$team} = $wins / $total;
    }

    if($setting eq "winrate"){
    # Print results
        print "Win rates (descending):\n";
        for my $team (sort { $winrate{$b} <=> $winrate{$a} } keys %winrate) {
            printf "%-25s : %.2f%% (%d/%d wins)\n", 
                $team, $winrate{$team}*100, $wins{$team}//0, $matches_played{$team};
        }
    }
    elsif($setting eq "mp"){
        # Print matches player
        print "Matches played (descending):\n";
        for my $team (sort { $matches_played{$b} <=> $matches_played{$a} } keys %matches_played) {
            printf "%-25s : %-25s\n", 
                $team, $matches_played{$team};
        }
    }
    else{
        print "Need valid setting, use 'winrate' for winrate or 'mp' for matches played";
    }
}
else {
    die $response->status_line;
}

sub getDataFromPostgres {
    my $myConnection = DBI->connect("DBI:Pg:dbname=matches;host=localhost", "postgres", "docker");
    my $query = $myConnection->prepare("SELECT * FROM matches");
    my $result = $query->execute();
    return $result
}