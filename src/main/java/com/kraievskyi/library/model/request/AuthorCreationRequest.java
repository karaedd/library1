package com.kraievskyi.library.model.request;

import lombok.Data;

@Data
public class AuthorCreationRequest {
    private String name;
    private String phrase;
}
