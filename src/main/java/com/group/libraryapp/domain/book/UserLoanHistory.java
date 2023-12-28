package com.group.libraryapp.domain.book;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id=null;

    public User getUser() {
        return user;
    }

    public String getBookName() {
        return bookName;
    }

    public boolean isReturn() {
        return isReturn;
    }

    @ManyToOne
    private User user;
    private String bookName;
    private boolean isReturn;

    public UserLoanHistory( User user,String bookName ) {
        this.user = user;
        this.bookName =bookName;
        this.isReturn = false;
    }
    protected UserLoanHistory(){
    }




    public void doReturn() {
        this.isReturn=true;
        }
    }
