package com.practice.GutenStore.model.dto.businessLogic;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

public record BookPageResponse(
        Page<DataBook> books
        ) {
}
