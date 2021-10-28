package com.example.demo.model.user;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.schedule.Professor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "post")
@Data
public class Posada extends BaseEntity {

    @Column(name = "short_post_professor")
    private String shortPostProfessor;

    @Column(name = "full_post_professor")
    private String fullPostProfessor;

    @JsonIgnore
    @OneToMany(mappedBy = "posada")
    private Collection<Professor> usersCollection;

    @Override
    public String toString() {
        return "Posada { shortPostProfessor: " + shortPostProfessor +
                ", fullPostProfessor: " + fullPostProfessor +
                " }";
    }
}
