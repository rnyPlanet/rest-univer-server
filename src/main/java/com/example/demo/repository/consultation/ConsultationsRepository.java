package com.example.demo.repository.consultation;

import com.example.demo.model.consultation.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface ConsultationsRepository extends JpaRepository<Consultation, Long> {

    @Modifying
    @Query(value = "insert into consultation_users (id_consultation, id_user) VALUES (:id_consultation, :id_user)", nativeQuery = true)
    @Transactional
    void subscribe(@Param("id_consultation") long id_consultation, @Param("id_user") long id_user);

    @Query(value = "SELECT c FROM Consultation c where c.createdUser = (select u from User u where u.username = :username)")
    List<Consultation> my(@Param("username") String username);

    @Query(value = "SELECT c FROM Consultation c order by c.dateOfPassage desc ")
    List<Consultation> findAll();

    @Modifying
    @Transactional
    @Query(value = "update Consultation c set c.room = (select r from Room r where r.id = :id_room), c.dateOfPassage = :date_of_passage, c.description = :description1 where c.id = :id")
    void update(@Param("id") long id, @Param("id_room") long id_room, @Param("date_of_passage") Date dateOfPassage, @Param("description1") String description);

    @Query(value = "select * from consultations where id IN (SELECT id_consultation FROM consultation_users where id_user = (select id from users where username = :username));", nativeQuery = true)
    @Transactional
    List<Consultation> mySubscriptions(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "delete from consultation_users where id_consultation = :idConsultation and id_user = :idUser", nativeQuery = true)
    void unsubscribe(@Param("idConsultation") long idConsultation, @Param("idUser") long idUser);

    @Modifying
    @Query("delete from Consultation c where c.id=:id")
    @Transactional
    void deleteConsultationById(@Param("id") Long id);
}
