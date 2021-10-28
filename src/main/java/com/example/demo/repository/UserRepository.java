package com.example.demo.repository;

import com.example.demo.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query(value = "SELECT * FROM users where BINARY username = :name", nativeQuery = true)
    User findByUsername(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET " +
            "u.patronymic = :patronymic, " +
            "u.firstName = :firstName, " +
            "u.lastName = :lastName, " +
            "u.lastNameInternational = :lastNameInternational, " +
            "u.firstNameInternational = :firstNameInternational,  " +
            "u.patronymicNameInternational = :patronymicNameInternational,  " +
            "u.email = :email,  " +
            "u.password = :password,  " +
            "u.photo.id = :id_photo,  " +
            "u.telephone_1 = :telephone_1,  " +
            "u.telephone_2 = :telephone_2  " +
            "WHERE u.id = :uId")
    void updateAcc(@Param("patronymic") String patronymic,
                   @Param("firstName") String firstName,
                   @Param("lastName") String lastName,
                   @Param("lastNameInternational") String lastNameInternational,
                   @Param("firstNameInternational") String firstNameInternational,
                   @Param("patronymicNameInternational") String patronymicNameInternational,
                   @Param("email") String email,
                   @Param("password") String password,
                   @Param("id_photo") Long id_photo,
                   @Param("telephone_1") String telephone_1,
                   @Param("telephone_2") String telephone_2,
                   @Param("uId") Long uId);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET " +
            "u.patronymic = :patronymic, " +
            "u.firstName = :firstName, " +
            "u.lastName = :lastName, " +
            "u.lastNameInternational = :lastNameInternational, " +
            "u.firstNameInternational = :firstNameInternational,  " +
            "u.patronymicNameInternational = :patronymicNameInternational,  " +
            "u.email = :email,  " +
            "u.photo.id = :id_photo,  " +
            "u.telephone_1 = :telephone_1,  " +
            "u.telephone_2 = :telephone_2  " +
            "WHERE u.id = :uId")
    void updateAcc(@Param("patronymic") String patronymic,
                   @Param("firstName") String firstName,
                   @Param("lastName") String lastName,
                   @Param("lastNameInternational") String lastNameInternational,
                   @Param("firstNameInternational") String firstNameInternational,
                   @Param("patronymicNameInternational") String patronymicNameInternational,
                   @Param("email") String email,
                   @Param("id_photo") Long id_photo,
                   @Param("telephone_1") String telephone_1,
                   @Param("telephone_2") String telephone_2,
                   @Param("uId") Long uId);
}
