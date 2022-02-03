package com.kraievskyi.library.repository;

import com.kraievskyi.library.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
