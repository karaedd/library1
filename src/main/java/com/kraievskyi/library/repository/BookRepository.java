package com.kraievskyi.library.repository;

import com.kraievskyi.library.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
