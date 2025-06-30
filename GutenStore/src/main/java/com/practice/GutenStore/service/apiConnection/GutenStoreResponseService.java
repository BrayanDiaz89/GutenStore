package com.practice.GutenStore.service.apiConnection;

import com.practice.GutenStore.model.dto.api.GutendexAPIResponse;
import com.practice.GutenStore.model.dto.businessLogic.DataBook;
import com.practice.GutenStore.model.dto.businessLogic.SaveResult;
import com.practice.GutenStore.model.repository.BookRepository;
import com.practice.GutenStore.service.apiConnection.components.SaveToDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GutenStoreResponseService {

    @Autowired
    private SaveToDB saveToDB;
    @Autowired
    private BookRepository bookRepository;

    public SaveResult serviceResponse(GutendexAPIResponse data){
        if(data.books() == null || data.books().isEmpty()){
            return null;
        }
        return saveToDB.saveBookToDB(data);
    }

    /*public Page<DataBook> serviceGetBooksActive(Pageable pageable) {
        return bookRepository.findByActiveTrue(pageable)
                .map(DataBook::new);
    }*/

}
