package com.police.RecognitionSystem.domain.services;

import com.police.RecognitionSystem.persistance.entities.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    private Person person;
    private String answer;
}
