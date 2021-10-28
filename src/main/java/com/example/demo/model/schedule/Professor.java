package com.example.demo.model.schedule;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.consultation.CustomSerializer;
import com.example.demo.model.user.Department;
import com.example.demo.model.user.Posada;
import com.example.demo.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "professors")
@Data
public class Professor extends BaseEntity {

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    private Collection<Classes> classesCollection;

    @JoinColumn(name = "id_post", referencedColumnName = "id")
    @ManyToOne
    private Posada posada;

    @JoinColumn(name = "id_department", referencedColumnName = "id")
    @ManyToOne
    private Department department;

    @JsonSerialize(using = CustomSerializer.class)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @OneToOne
    private User user;

    @Override
    public String toString() {
        return "Professor { posada: " + posada +
                ", department: " + department +
                " }";
    }

}
