package com.example.demo.repository;

import com.example.demo.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.user = (SELECT u FROM User u WHERE u.id = :id)")
    Student findByUserId(@Param("id") Long id);

}
