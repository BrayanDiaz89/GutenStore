package com.practice.GutenStore.model.dto.businessLogic;

import org.springframework.data.domain.Page;

public record AuthorsBooksPageResponse(
        Page<AuthorsWithBooks> authors
) {
}
