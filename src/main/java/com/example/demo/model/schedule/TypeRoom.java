package com.example.demo.model.schedule;

import com.example.demo.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "type_room")
@Data
public class TypeRoom extends BaseEntity {

    @Column(name = "type")
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private List<Room> rooms;

    @Override
    public String toString() {
        return "TypeRoom { type: " + type + " }";
    }
}