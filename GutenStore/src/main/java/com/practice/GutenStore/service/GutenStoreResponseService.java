package com.practice.GutenStore.service;

import com.practice.GutenStore.model.dto.api.DataBookDTO;
import com.practice.GutenStore.model.dto.model.DataBookResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class GutenStoreResponseService {

    public DataBookResponseDTO serviceResponse(DataBookDTO data){
        return new DataBookResponseDTO(
                data.title(),
                data.authors(),
                data.languages(),
                data.formats(),
                data.numberDownloads()
        );
    }
}
