package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.book.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    public User(String name,Integer age) {
        if(name==null||name.isBlank()){
            throw new IllegalArgumentException(String.format("널값"));
        }
        this.name = name;
        this.age = age;
    }
    @Column(name="age",length = 20)
    private Integer age;
    @Column(name="name",nullable = false)
    private  String name;

    protected User(){
    }

    public Long getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
    public void update(String name){
        this.name=name;
    }

    public User(Long id, Integer age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
    public void loanBook(String bookName) {
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }
    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow( IllegalArgumentException::new);
        targetHistory.doReturn();
    }




}
