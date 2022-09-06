package com.example.PP;
import org.hibernate.mapping.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

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

    @GetMapping("/KATIE")
    public ResponseEntity<List<League_Table>> getTable() {
        List<League_Table> league_table =league_tableRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(league_table);
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
