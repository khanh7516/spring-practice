package com.example.jpatest.repository;

import com.example.jpatest.dto.UserDto;
import com.example.jpatest.entity.User;
import com.example.jpatest.projection.UserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByNameContainingIgnoreCase(String name);
    @Query("SELECT NEW com.example.jpatest.dto.UserDto(u.id, u.name, u.email) FROM User u WHERE u.name = :name")
    UserDto findUserDtoByName(@Param("name") String name);

    @Query(name = "getUserDto", nativeQuery = true)
    UserDto findUserDtoNativeQuery(@Param("name") String name);

    UserView findUserByName(String name);
}