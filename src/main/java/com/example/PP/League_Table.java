package com.example.PP;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class League_Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TeamID;
    private Timestamp dateCreated;
    private String TeamName;
    @Column(columnDefinition = "int default 0")
    private int Won;
    @Column(columnDefinition = "int default 0")
    private int Drawn;
    @Column(columnDefinition = "int default 0")
    private int Lost;
    @Column(columnDefinition = "int default 0")
    private int Points;

    public League_Table(int TeamID, String TeamName, int Won, int Drawn, int Lost, int Points) {
        this.TeamID = TeamID;
        this.dateCreated = new Timestamp(new Date().getTime());
        this.TeamName = TeamName;
        this.Won = Won;
        this.Drawn = Drawn;
        this.Lost = Lost;
        this.Points = (3 * Won) + Drawn ;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public int getWon() {
        return Won;
    }

    public void setWon(int won) {
        Won = won;
    }

    public int getDrawn() {
        return Drawn;
    }

    public void setDrawn(int drawn) {
        Drawn = drawn;
    }

    public int getLost() {
        return Lost;
    }

    public void setLost(int lost) {
        Lost = lost;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }
    public League_Table() {
        this.dateCreated = new Timestamp(new Date().getTime());
    }



    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}