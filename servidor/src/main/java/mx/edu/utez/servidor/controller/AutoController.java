package mx.edu.utez.servidor.controller;


import mx.edu.utez.servidor.model.Auto;
import mx.edu.utez.servidor.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/autos")
public class AutoController {

    private final AutoService autoService;

    @Autowired
    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Auto>> getAllAutos() {
        return new ResponseEntity<>(autoService.getAllAutos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auto> getAutoById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(autoService.getAutoById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Auto> createAuto(@Valid @RequestBody Auto auto) {
        try {
            return new ResponseEntity<>(autoService.createAuto(auto), HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auto> updateAuto(@PathVariable String id, @Valid @RequestBody Auto autoDetails) {
        try {
            return new ResponseEntity<>(autoService.updateAuto(id, autoDetails), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuto(@PathVariable String id) {
        try {
            autoService.deleteAuto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
