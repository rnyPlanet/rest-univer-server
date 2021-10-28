package com.example.demo.repository.schedule;

import com.example.demo.model.schedule.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassesRepository extends JpaRepository<Classes, Long> {

    @Query("select c from Classes c where c.group = (SELECT g FROM Group g WHERE g.name = :name)")
    List<Classes> findByName(@Param("name") String name);

    @Query("select c from Classes c where c.group = (SELECT g FROM Group g WHERE g.name = :name) order by c.place, c.positionInDay asc ")
    List<Classes> orderByPlaceAndIndexInDay(@Param("name") String name);
}
