package com.example.demo.repository;

import com.example.demo.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Integer> {

    @Query(value ="select * from ACCOUNT where USER = ?1", nativeQuery = true)
    List<AccountEntity> getAllAccounts(int documentUser);
}