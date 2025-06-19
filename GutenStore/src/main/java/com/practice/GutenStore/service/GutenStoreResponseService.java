package com.practice.GutenStore.service;

import com.practice.GutenStore.model.dto.api.DataBook;
import com.practice.GutenStore.model.dto.api.GutendexAPIResponse;
import org.springframework.stereotype.Service;

@Service
public class GutenStoreResponseService {

    public DataBook serviceResponse(GutendexAPIResponse data){
        if(data.books() == null || data.books().isEmpty()){
            return null;
        }
        DataBook dataBook = data.books().get(0);
        return dataBook;
    }
}
