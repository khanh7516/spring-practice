package com.example.jpatest.repository;

import com.example.jpatest.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Tìm tất cả các Employee theo emailAddress và lastName
    List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);

    // Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
    @Query("SELECT e FROM Employee e WHERE e.firstName <> :firstName OR e.lastName <> :lastName")
    List<Employee> findDistinctByFirstNameOrLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    // Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần
    List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);

    // Tìm tất cả các Employee theo firstName không phân biệt hoa thường
    List<Employee> findByFirstNameIgnoreCase(String firstName);
    Page<Employee> findAll(Pageable pageable);

}