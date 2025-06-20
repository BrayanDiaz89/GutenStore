package com.practice.GutenStore.service.apiConnection;

import com.practice.GutenStore.model.dto.businessLogic.DataBook;
import com.practice.GutenStore.model.dto.api.GutendexAPIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GutenStoreResponseService {

    @Autowired
    private SaveToDB saveToDB;

    public DataBook serviceResponse(GutendexAPIResponse data){
        if(data.books() == null || data.books().isEmpty()){
            return null;
        }
        var dataBook = saveToDB.saveBookToDB(data);
        return  dataBook;
    }
}
