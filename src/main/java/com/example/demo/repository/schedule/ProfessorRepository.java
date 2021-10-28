package com.example.demo.repository.schedule;

import com.example.demo.model.schedule.Classes;
import com.example.demo.model.schedule.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findById(long id);
    
    @Query(value = "SELECT c FROM Classes c where c.professor = (select p from Professor p where p.id = :id)")
//    @Query(value = "select * from classes where professor_id = (select id from professors where name LIKE '?1%');", nativeQuery = true)
    @Transactional
    List<Classes> getClassesByProfessorId(@Param("id") Long id);
    
    
    @Query("select c from Classes c where c.professor = (select p from Professor p where p.user = (select u from User u where u.id = :id)) order by c.place, c.positionInDay asc")
    List<Classes> orderByPlaceAndPositionInDay(@Param("id") Long id);
    
    @Query("SELECT p FROM Professor p WHERE p.user = (SELECT u FROM User u WHERE u.id = :id)")
    Professor findByUserId(@Param("id") Long id);
}
