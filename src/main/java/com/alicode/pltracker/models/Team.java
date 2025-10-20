package com.alicode.pltracker.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "teams")
public class Team {
    @Id
    private Long team_id;
    private String teamName;
    private String venue;
    private String ownerName;
    private String logo_url;

    public Long getTeamId(){
        return team_id;
    }

    public void setTeamId(long team_id){
        this.team_id = team_id;
    }

    public String getTeamName(){
        return teamName;
    }

    public void setTeamName(String team_name){
        this.teamName = team_name;
    }

    public String getVenue(){
        return venue;
    }

    public void setVenue(String venue){
        this.venue = venue;
    }

    public String getOwnerName(){
        return ownerName;
    }

    public void setOwnerName(String ownerName){
        this.ownerName = ownerName;
    }

    public String getLogoUrl(){
        return logo_url;
    }

    public void setLogoUrl(String logo_url){
        this.logo_url = logo_url;
    }
}
