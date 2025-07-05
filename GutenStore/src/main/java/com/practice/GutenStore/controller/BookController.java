package com.practice.GutenStore.controller;

import com.practice.GutenStore.model.dto.businessLogic.BookPageResponse;
import com.practice.GutenStore.model.dto.businessLogic.DataBook;
import com.practice.GutenStore.model.dto.api.RequestDTO;
import com.practice.GutenStore.service.apiConnection.GetDataGutendexService;
import com.practice.GutenStore.service.apiConnection.GutenStoreResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Consult")
public class BookController {

    @Autowired
    private GetDataGutendexService getData;
    @Autowired
    private GutenStoreResponseService gutenStoreService;

    @PostMapping("/book")
    public Mono<ResponseEntity<DataBook>> getDataGutendex(@RequestBody @Valid RequestDTO request) {
        return getData.getDataFromGutendex(request)
                .map(gutenStoreService::serviceResponse)
                .map(saveResult -> {
                    HttpStatus status = saveResult.alreadyExists() ? HttpStatus.OK : HttpStatus.CREATED;
                    return ResponseEntity.status(status).body(saveResult.dataBook());
                });
    }

    //Listar libros en base de datos
    @GetMapping("/books")
    public ResponseEntity<BookPageResponse> getAllBooksActive(@PageableDefault(size = 5) Pageable pageable){
        var booksPage = gutenStoreService.serviceGetBooksActive(pageable);
        return ResponseEntity.ok().body(new BookPageResponse(booksPage));
    }

}
