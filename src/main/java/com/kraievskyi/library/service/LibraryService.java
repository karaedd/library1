package com.kraievskyi.library.service;

import com.kraievskyi.library.exception.EntityNotFoundException;
import com.kraievskyi.library.model.Author;
import com.kraievskyi.library.model.Book;
import com.kraievskyi.library.model.request.AuthorCreationRequest;
import com.kraievskyi.library.model.request.BookCreationRequest;
import com.kraievskyi.library.repository.AuthorRepository;
import com.kraievskyi.library.repository.BookRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public Author createAuthor(AuthorCreationRequest author) {
        Author authorToCreate = new Author();
        BeanUtils.copyProperties(author, authorToCreate);
        authorToCreate.setName(author.getName());

        return authorRepository.save(authorToCreate);
    }

    public Author getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author.get();
        }
        throw new EntityNotFoundException("Can't find any author under given id");
    }

    public Author updateAuthor(Long id, AuthorCreationRequest request) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (!optionalAuthor.isPresent()) {
            throw new EntityNotFoundException("Author not present in the database");
        }
        Author author = optionalAuthor.get();
        author.setName(request.getName());
        return authorRepository.save(author);
    }

    public void deleteAllAuthors() {

        authorRepository.deleteAll();
    }

    public void deleteAuthor(Long id) {

        authorRepository.deleteById(id);
    }

    public Book addAuthorToBook(Book book, Author author) {
        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        Optional<Author> optionalAuthor = authorRepository.findById(author.getId());

        Book bookById = optionalBook.orElseThrow(() ->
                new RuntimeException("Book is not present in the database"));
        Author authorById = optionalAuthor.orElseThrow(() ->
                new RuntimeException("Author is not present in the database"));

        if (bookById.getAuthor().contains(authorById)) {
            throw new RuntimeException("Author with id "
                    + authorById.getId() + " already exists");
        }
        bookById.getAuthor().add(authorById);
        return bookRepository.save(bookById);
    }

    public List<Author> findAuthorsByBookName(String name) {

        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().forEach(bookList::add);

        return bookList.stream()
                .filter(book -> book.getTitle().contains(name))
                .map(Book::getAuthor)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public Book createBook(BookCreationRequest book) {
        Book bookToCreate = new Book();
        BeanUtils.copyProperties(book, bookToCreate);
        bookToCreate.setTitle(book.getTitle());
        bookToCreate.setAuthor(new ArrayList<>());
        return bookRepository.save(bookToCreate);

    }

    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new EntityNotFoundException("Can't find any book under given id");
    }

    public Book updateBook(Long id, BookCreationRequest request) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            throw new EntityNotFoundException("Book is not present in the database");
        }
        Book book = optionalBook.get();
        book.setTitle(request.getTitle());
        return bookRepository.save(book);
    }

    public void deleteAllBooks() {

        bookRepository.deleteAll();
    }

    public void deleteBook(Long id) {

        bookRepository.deleteById(id);
    }
}
