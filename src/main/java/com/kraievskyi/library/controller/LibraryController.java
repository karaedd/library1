package com.kraievskyi.library.controller;

import com.kraievskyi.library.model.Author;
import com.kraievskyi.library.model.Book;
import com.kraievskyi.library.model.request.AuthorCreationRequest;
import com.kraievskyi.library.model.request.BookCreationRequest;
import com.kraievskyi.library.service.LibraryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorCreationRequest request) {
        return ResponseEntity.ok(libraryService.createAuthor(request));
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(libraryService.getAuthorById(id));
    }

    @GetMapping("/book")
    public List<Author> getAuthor(@RequestParam(required = false) String name) {
        return libraryService.findAuthorsByBookName(name);
    }

    @PatchMapping("/author/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") Long id,
                                               @RequestBody AuthorCreationRequest request) {
        return ResponseEntity.ok(libraryService.updateAuthor(id, request));
    }

    @DeleteMapping("/author")
    public ResponseEntity<Void> deleteAllAuthors() {
        libraryService.deleteAllAuthors();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        libraryService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addAuthor/{bookId}/{authorId}")
    public ResponseEntity<Book> addAuthor(
            @PathVariable Long bookId, @PathVariable Long authorId) {
        return ResponseEntity.ok(libraryService.addAuthorToBook(
                libraryService.getBookById(bookId),
                libraryService.getAuthorById(authorId)));
    }

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody BookCreationRequest request) {
        return ResponseEntity.ok(libraryService.createBook(request));
    }

    @DeleteMapping("/book")
    public ResponseEntity<Void> deleteAllBooks() {
        libraryService.deleteAllBooks();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(libraryService.getBookById(id));
    }

    @PatchMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id,
                                               @RequestBody BookCreationRequest request) {
        return ResponseEntity.ok(libraryService.updateBook(id, request));
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        libraryService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
