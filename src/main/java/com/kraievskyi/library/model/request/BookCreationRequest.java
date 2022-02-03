package com.kraievskyi.library.model.request;

import lombok.Data;

@Data
public class BookCreationRequest {
    private String title;
    private Long authorId;
    private Long pageId;
}
