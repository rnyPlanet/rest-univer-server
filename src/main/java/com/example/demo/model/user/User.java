package com.example.demo.model.user;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.Status;
import com.example.demo.model.consultation.Consultation;
import com.example.demo.model.schedule.Professor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "last_name_international")
    private String lastNameInternational;

    @Column(name = "first_name_international")
    private String firstNameInternational;

    @Column(name = "patronymic_name_international")
    private String patronymicNameInternational;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Student student;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Professor professor;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "id_user", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_role", referencedColumnName = "id")})
    private List<Role> roles;

    @JsonIgnore
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<Consultation> subscribeOn;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUser")
    private List<Consultation> created;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_photo", referencedColumnName = "id")
    private Photo photo;

    private String telephone_1;
    private String telephone_2;

    @Override
    public String toString() {
        return "username: " + username +
                ", patronymic: " + patronymic +
                ", firstName: " + firstName +
                ", lastName: " + lastName +
                ", email: " + email +
                ", roles: " + roles +
                ", status: " + status +
                ", photo: " + photo;
    }

}
