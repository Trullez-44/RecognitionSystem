package com.police.RecognitionSystem.domain.services;

import com.police.RecognitionSystem.domain.repositories.PersonRepository;
import com.police.RecognitionSystem.domain.services.Answer;
import com.police.RecognitionSystem.persistance.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person savePerson(Person person) {

        return personRepository.save(person);
    }
    public boolean deletePerson(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            personRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public boolean updatePerson(Long id, Person updatedPerson) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person personExist = optionalPerson.get();
            personExist.setNombre(updatedPerson.getNombre());
            personExist.setApellido(updatedPerson.getApellido());
            personExist.setDireccion(updatedPerson.getDireccion());
            personExist.setEmail(updatedPerson.getEmail());
            personExist.setCromosoma(updatedPerson.getCromosoma());
            personRepository.save(personExist);
            return true;
        } else {
            return false;
        }
    }
    public Answer compararADNConBaseDeDatos(String muestraADN) {
        List<Person> personas = personRepository.findAll();
        int mejorCoincidencia = 0;
        Person sospechoso = null;
        for (Person persona : personas) {
            int coincidencias = calcularCoincidencias(persona.getCromosoma(), muestraADN);
            if (coincidencias > mejorCoincidencia) {
                mejorCoincidencia = coincidencias;
                sospechoso = persona;
            }
        }
        double porcentajeParentesco = ((double) mejorCoincidencia / muestraADN.length()) * 100;
        String respuesta = String.format("Sospechoso: %s %s - Porcentaje de parentesco: %.2f%%",
                sospechoso.getNombre(), sospechoso.getApellido(), porcentajeParentesco);
        return new Answer(sospechoso, respuesta);
    }
    private int calcularCoincidencias(String cromosomaPerson, String muestraADN) {
        int coincidencias = 0;
        for (int i = 0; i < cromosomaPerson.length(); i++) {
            if (cromosomaPerson.charAt(i) == muestraADN.charAt(i)) {
                coincidencias++;
            }
        }
        return coincidencias;
    }
}