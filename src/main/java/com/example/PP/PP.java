package com.example.PP;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class PP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp dateCreated;
    private int match_id;
    private int homeTeamID;
    @Column(nullable = true)
    private Integer homeTeam_Score;
    private int awayTeamID;
    @Column(nullable = true)
    private Integer awayTeam_Score;

    public PP(int id, int match_id, int homeTeamID, Integer homeTeam_Score, int awayTeamID, Integer awayTeam_Score) {
        this.id = id;
        this.dateCreated = new Timestamp(new Date().getTime());
        this.match_id = match_id;
        this.homeTeamID = homeTeamID;
        this.homeTeam_Score = homeTeam_Score;
        this.awayTeamID = awayTeamID;
        this.awayTeam_Score = awayTeam_Score;
    }


    public int getHomeTeamID() {
        return homeTeamID;
    }

    public void setHomeTeamID(int homeTeamID) {
        this.homeTeamID = homeTeamID;
    }

    public Integer getHomeTeam_Score() {
        return homeTeam_Score;
    }

    public void setHomeTeam_Score(Integer homeTeam_Score) {
        this.homeTeam_Score = homeTeam_Score;
    }

    public int getAwayTeamID() {
        return awayTeamID;
    }

    public void setAwayTeamID(int awayTeamID) {
        this.awayTeamID = awayTeamID;
    }

    public Integer getAwayTeam_Score() {
        return awayTeam_Score;
    }

    public void setAwayTeam_Score(Integer awayTeam_Score) {
        this.awayTeam_Score = awayTeam_Score;
    }

    public PP() {
        this.dateCreated = new Timestamp(new Date().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }



}