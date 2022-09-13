package com.example.PP;
import org.hibernate.mapping.Array;
import org.hibernate.mapping.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;

@CrossOrigin
@RestController
public class PPController {

    @Autowired
    PPRepository ppRepository;
    @Autowired
    League_TableRepository league_tableRepository;

    @GetMapping("/pp/{id}")
    public ResponseEntity<PP> getPPById(@PathVariable String id) {
        PP pp = ppRepository.findById(parseInt(id)).orElse(null);
        HttpStatus status = pp == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).body(pp);
    }

    @GetMapping("/pps")
    public ResponseEntity<List<PP>> getPP() {
        List<PP> pp = ppRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pp);
    }

    @GetMapping("/Table")
    public ResponseEntity<List<League_Table>> getTable() {
        List<League_Table> league_table =league_tableRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(league_table);
    }

    @PutMapping("/predictions")
    public  ResponseEntity<PP> updatePrediction(@RequestBody PP pp ) {
        PP prediction = ppRepository.findById(pp.getId()).orElse(null);
        prediction.setHomeTeam_Score(pp.getHomeTeam_Score());
        prediction.setAwayTeam_Score(pp.getAwayTeam_Score());
        ppRepository.save(prediction);

        League_Table homeTeamUpdate = league_tableRepository.findById(prediction.getHomeTeamID()).orElse(null);
        if (pp.getHomeTeam_Score() > pp.getAwayTeam_Score()){
            homeTeamUpdate.setWon(homeTeamUpdate.getWon() +1);
            homeTeamUpdate.setPoints(homeTeamUpdate.getPoints() + 3);
        } else if (pp.getHomeTeam_Score() == pp.getAwayTeam_Score()){
            homeTeamUpdate.setDrawn(homeTeamUpdate.getDrawn() +1);
            homeTeamUpdate.setPoints(homeTeamUpdate.getPoints() + 1);
        } else if (pp.getHomeTeam_Score() < pp.getAwayTeam_Score()){
            homeTeamUpdate.setLost(homeTeamUpdate.getLost() +1);
        }
        league_tableRepository.save(Objects.requireNonNull(homeTeamUpdate));

        League_Table awayTeamUpdate = league_tableRepository.findById(prediction.getAwayTeamID()).orElse(null);
        if (pp.getHomeTeam_Score() < pp.getAwayTeam_Score()){
            awayTeamUpdate.setWon(awayTeamUpdate.getWon() +1);
            awayTeamUpdate.setPoints(awayTeamUpdate.getPoints() + 3);
        } else if (pp.getHomeTeam_Score() == pp.getAwayTeam_Score()){
            awayTeamUpdate.setDrawn(awayTeamUpdate.getDrawn() +1);
            awayTeamUpdate.setPoints(awayTeamUpdate.getPoints() + 1);
        } else if (pp.getHomeTeam_Score() > pp.getAwayTeam_Score()){
            awayTeamUpdate.setLost(awayTeamUpdate.getLost() +1);
        }
        league_tableRepository.save(Objects.requireNonNull(awayTeamUpdate));


    return new ResponseEntity<>(prediction,HttpStatus.OK);
    }


    @GetMapping("/random-pp")
    public ResponseEntity<PP> getRandom() {
        List<PP> pp = ppRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(pp.size());
        PP randomPP = pp.get(randomIndex);
        return ResponseEntity.status(HttpStatus.OK).body(randomPP);
    }


    @PostMapping("pp")
    public ResponseEntity createPP(@RequestBody PP pp) {
        ppRepository.save(pp);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/pp/{id}")
    public ResponseEntity deletePP(@PathVariable String id) {
        PP ppToDelete = ppRepository.findById(parseInt(id)).orElse(null);

        if (ppToDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        ppRepository.delete(ppToDelete);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
