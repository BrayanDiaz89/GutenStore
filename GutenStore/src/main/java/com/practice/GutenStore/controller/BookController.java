package com.practice.GutenStore.controller;

import com.practice.GutenStore.model.dto.businessLogic.DataBook;
import com.practice.GutenStore.model.dto.api.RequestDTO;
import com.practice.GutenStore.service.apiConnection.GetDataGutendexService;
import com.practice.GutenStore.service.apiConnection.GutenStoreResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Consult")
public class BookController {

    @Autowired
    private GetDataGutendexService getData;
    @Autowired
    private GutenStoreResponseService gutenStoreService;

    @PostMapping("/book")
    public ResponseEntity<Mono<DataBook>> getDataGutendex(@RequestBody @Valid RequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(getData.getDataFromGutendex(request)
                .map(gutenStoreService::serviceResponse));
    }
}
