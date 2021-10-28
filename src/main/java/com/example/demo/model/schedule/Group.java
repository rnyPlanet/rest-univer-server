package com.example.demo.model.schedule;

import com.example.demo.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "groups")
@Data
public class Group extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Short size;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private Collection<Classes> classesCollection;

    @Override
    public String toString() {
        return "Group { name: " + name +
                ", size: " + size +
                " }";
    }

}
