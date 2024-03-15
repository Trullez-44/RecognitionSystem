package com.police.RecognitionSystem.controller;

import com.police.RecognitionSystem.domain.services.Answer;
import com.police.RecognitionSystem.domain.services.PersonService;
import com.police.RecognitionSystem.persistance.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        List<Person> personas = personService.getAll();
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getOneById(@PathVariable Long id) {
        Person persona = personService.getById(id);
        if (persona != null) {
            return ResponseEntity.ok(persona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> postPerson(@RequestBody Person persona) {
        personService.savePerson(persona);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        boolean deleted = personService.deletePerson(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putPerson(@PathVariable Long id, @RequestBody Person persona) {
        boolean updated = personService.updatePerson(id, persona);
        if (updated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/comparar-adn")
    public ResponseEntity<Answer> compararADNConBaseDeDatos(@RequestParam String cromosoma) {
        Answer answer = personService.compararADNConBaseDeDatos(cromosoma);
        if (answer != null) {
            return ResponseEntity.ok(answer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
