package com.practice.GutenStore.service.apiConnection;

import com.practice.GutenStore.model.dto.api.GutendexAPIResponse;
import com.practice.GutenStore.model.dto.businessLogic.SaveResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GutenStoreResponseService {

    @Autowired
    private SaveToDB saveToDB;

    public SaveResult serviceResponse(GutendexAPIResponse data){
        if(data.books() == null || data.books().isEmpty()){
            return null;
        }
        return saveToDB.saveBookToDB(data);
    }
}
