package com.group.libraryapp.repstory.user.userloan;

import com.group.libraryapp.domain.book.UserLoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanHistoryRep extends JpaRepository<UserLoanHistory,Long> {

    boolean existsByBookNameAndIsReturn(String name,boolean isReturn);
    Optional<UserLoanHistory> findByUserIdAndBookName(long userId, String bookName);
}
