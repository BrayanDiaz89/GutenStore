package com.practice.GutenStore.model.entities;

import com.practice.GutenStore.model.dto.api.FormatsDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Formats {

    @Column(name = "poster")
    private String poster;

    @Column(name = "e_book)")
    private String e_book;

    public Formats(FormatsDTO formats){
        this.poster = formats.poster();
        this.e_book = formats.e_book();
    }

}
