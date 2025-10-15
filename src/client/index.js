const API_BASE = "http://localhost:8080";

const teamSelection = document.querySelector('#team-select');
const tableBody = document.querySelector('#table-body')
teamSelection.addEventListener('change', async () => {
    const options = {
      method: "POST",
      headers: {
        Accept: "text/xml",
        'Content-Type': 'text/xml',
      },
      body: `<?xml version="1.0" encoding="utf-8"?>
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
				  xmlns:gs="match.pltracker.com">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <gs:getMatchRequest>
                            <gs:homeTeam>${teamSelection.value}</gs:homeTeam>
                        </gs:getMatchRequest>
                    </soapenv:Body>
                    </soapenv:Envelope>`
    };
    tableBody.innerHTML = ''
    const response = await fetch(`http://localhost:8080/ws`, options)
    if (!response.ok) throw new Error("Failed to fetch requests")
    const requests = await response.text()
    results = getMatchDetailsFromSoap(requests)
    console.log(results);
    results.forEach(element => {
        create_elements(element)
    });
})

function create_elements(element) {
    const row = document.createElement("tr")
    const date = document.createElement("td")
    date.textContent = element.match_date
    const home = document.createElement("td")
    home.textContent = element.homeTeam
    const away = document.createElement("td")
    away.textContent = element.awayTeam
    const score = document.createElement("td")
    score.textContent = `${element.home_score}-${element.away_score}`
    const win = document.createElement("td")
    if(element.homeTeam == teamSelection.value && element.home_score > element.away_score){
        win.textContent = "Win"
    }
    else if(element.awayTeam == teamSelection.value && element.home_score < element.away_score){
        win.textContent = "Win"
    }
    else if(element.home_score == element.away_score){
        win.textContent = "Draw"
    }
    else {
        win.textContent = "Lose"
    }
    row.appendChild(date)
    row.appendChild(home)
    row.appendChild(score)
    row.appendChild(away)
    row.appendChild(win)
    tableBody.appendChild(row)
}

function getMatchDetailsFromSoap(xmlText, opts = { parseNumbers: true, parseDates: false }) {
  const doc = new DOMParser().parseFromString(xmlText, "application/xml");
  if (doc.getElementsByTagName("parsererror").length) {
    throw new Error("Invalid XML");
  }

  // find <matchDetails> nodes by localName (ignores namespace prefix)
  const all = Array.from(doc.getElementsByTagName("*"));
  const matchNodes = all.filter(n => n.localName === "matchDetails");

  const results = matchNodes.map(node => {
    const obj = {};
    // iterate element children of each matchDetails
    Array.from(node.children).forEach(child => {
      const key = child.localName;               // e.g. "match_id", "homeTeam"
      let value = child.textContent.trim();

      if (opts.parseNumbers && /^[0-9]+$/.test(value)) {
        // safe integer conversion
        value = parseInt(value, 10);
      } else if (opts.parseDates && /^\d{4}-\d{2}-\d{2}/.test(value)) {
        value = new Date(value); // convert ISO-like date strings
      }

      obj[key] = value;
    });
    return obj;
  });

  return results;
}