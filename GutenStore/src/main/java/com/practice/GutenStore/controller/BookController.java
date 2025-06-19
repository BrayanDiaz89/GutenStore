package com.practice.GutenStore.controller;

import com.practice.GutenStore.model.dto.api.DataBook;
import com.practice.GutenStore.model.dto.api.RequestDTO;
import com.practice.GutenStore.service.GetDataGutendexService;
import com.practice.GutenStore.service.GutenStoreResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Mono<DataBook> getDataGutendex(@RequestBody @Valid RequestDTO request){
        return getData.getDataFromGutendex(request)
                .map(gutenStoreService::serviceResponse);
    }
}
