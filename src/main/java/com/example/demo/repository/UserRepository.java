package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import lombok.Generated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Generated
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
}