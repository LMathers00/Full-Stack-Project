package com.example.PP;

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

    @GetMapping("/pp/{id}")
    public ResponseEntity<PP> getPPById(@PathVariable String id) {
        PP pp = ppRepository.findById(parseInt(id)).orElse(null);
        HttpStatus status = pp == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).body(pp);
    }

    @GetMapping("/pps")
    public ResponseEntity<List<PP>> getPPs() {
        List<PP> quotes = ppRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(quotes);
    }

    @GetMapping("/random-pp")
    public ResponseEntity<PP> getRandom() {
        List<PP> pps = ppRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(pps.size());
        PP randomPP = pps.get(randomIndex);
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

