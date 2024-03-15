package com.police.RecognitionSystem.domain.repositories;

import com.police.RecognitionSystem.persistance.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
