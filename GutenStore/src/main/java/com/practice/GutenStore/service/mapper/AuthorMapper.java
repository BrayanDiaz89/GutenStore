package com.practice.GutenStore.service.mapper;

import com.practice.GutenStore.model.dto.businessLogic.AuthorDTO;
import com.practice.GutenStore.model.entities.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDTO toDataAuthor(Author author){
        if(author == null){
            return null;
        }
        return new AuthorDTO(
                author.getNameAuthor(),
                author.getBirthYear(),
                author.getDeathYear()
        );
    }

}
