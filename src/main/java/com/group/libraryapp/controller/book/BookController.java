package com.group.libraryapp.controller.book;


import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepstory;
import com.group.libraryapp.dto.book.request.BookLoanReq;
import com.group.libraryapp.dto.book.request.BookRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private BookRepstory bookRepstory;
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public void savebook(@RequestBody BookRequest book) {
        bookService.saveBook(book);
}

    @PostMapping("/book/loan")
    public void loan(@RequestBody BookLoanReq req){
     bookService.loan(req);
    }
    @PutMapping("/book/return")
    public void returnBook(@RequestBody BookReturnRequest request) {
        bookService.returnBook(request);
    }
}
