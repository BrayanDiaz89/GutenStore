package com.practice.GutenStore.model.dto.businessLogic;

import java.util.List;

public record ListBooksResponse(
        List<DataBook> books
) {
}
