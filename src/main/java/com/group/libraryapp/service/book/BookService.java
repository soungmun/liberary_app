package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepstory;
import com.group.libraryapp.domain.book.UserLoanHistory;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepstrory;
import com.group.libraryapp.dto.book.request.BookLoanReq;
import com.group.libraryapp.dto.book.request.BookRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import com.group.libraryapp.repstory.user.userloan.UserLoanHistoryRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookService {

    @Autowired
    private final BookRepstory bookRepstory;
    @Autowired
    private final UserLoanHistoryRep userLoanHistoryRep;
    @Autowired
    private final UserRepstrory userRepstrory;
    public BookService(BookRepstory bookRepstory, UserLoanHistoryRep userLoanHistoryRep, UserRepstrory userRepstrory) {
        this.bookRepstory = bookRepstory;
        this.userLoanHistoryRep = userLoanHistoryRep;
        this.userRepstrory = userRepstrory;
    }

    @Transactional
    public void saveBook(BookRequest req) {
        bookRepstory.save(new Book(req.getName()));
    }

    @Transactional
    public void loan(BookLoanReq req) {
        Book book = bookRepstory.findByName(req.getBookName()).orElseThrow(IllegalArgumentException::new);
        if (userLoanHistoryRep.existsByBookNameAndIsReturn(book.getName(), false)){
            throw new IllegalArgumentException("대출중인 책입니다.");
        }
        User user=userRepstrory.findByName(req.getUserName()).orElseThrow(IllegalArgumentException::new);
            userLoanHistoryRep.save( new UserLoanHistory( user,book.getName()));
      }

    @Transactional
    public void loanBook(BookLoanReq request) {
        Book book = bookRepstory.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        if (userLoanHistoryRep.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("진작 대출되어 있는 책입니다");
        }
        User user = userRepstrory.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.loanBook(book.getName()); // 바뀐 코드
    }
    @Transactional
    public void returnBook(BookReturnRequest req) {
        User user = userRepstrory.findByName(req.getBookName()).orElseThrow(IllegalArgumentException::new);
        if(user==null){
            throw new IllegalArgumentException("도서대출내역이 없습니다");
        }
        user.returnBook(req.getUserName());
    }
}
