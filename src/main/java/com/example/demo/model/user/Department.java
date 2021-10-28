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
@Table(name = "department")
@Data
public class Department extends BaseEntity {

    @JsonIgnore
    @Column(name = "pkey_type_department")
    private Integer pkeyTypeDepartment;

    @JsonIgnore
    @Column(name = "pkey_boss")
    private Integer pkeyBoss;

    @Column(name = "name")
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "email")
    private String emeilemeil;

    @Column(name = "telephone")
    private String telefon;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private Collection<Professor> userCollection;

    @Override
    public String toString() {
        return "Department { pkeyTypeDepartment: " + pkeyTypeDepartment +
                ", pkeyBoss: " + pkeyBoss +
                ", name: " + name +
                ", shortName: " + shortName +
                ", emeilemeil: " + emeilemeil +
                ", telefon: " + telefon +
                " }";
    }

}
