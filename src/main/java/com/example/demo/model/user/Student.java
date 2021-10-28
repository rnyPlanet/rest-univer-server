package com.example.demo.model.user;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.SubGroup;
import com.example.demo.model.schedule.Group;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Data
public class Student extends BaseEntity {

    @JsonIgnore
    @JoinColumn(name = "id_user", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private User user;

    @JoinColumn(name = "id_group", referencedColumnName = "id")
    @OneToOne
    private Group group;

    @Column(name = "subgroup")
    @Enumerated(EnumType.STRING)
    private SubGroup subGroup;

    @Override
    public String toString() {
        return "Student { group: " + group +
                ", subGroup: " + subGroup +
                " }";
    }

}
