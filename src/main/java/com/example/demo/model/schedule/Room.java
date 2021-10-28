package com.example.demo.model.schedule;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.consultation.Consultation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "rooms")
@Data
public class Room extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "count_places")
    private Short countPlaces;

    @Column(name = "count_pcs")
    private Short countPCs;

    @Column(name = "is_blocked")
    private Short isBlocked;

    @ManyToOne
    @JoinColumn(name = "type")
    private TypeRoom type;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Collection<Classes> classesCollection;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private Collection<Consultation> consultationsCollection;

    @Override
    public String toString() {
        return "Room { name: " + name +
                ", countPlaces: " + countPlaces +
                ", isBlocked: " + isBlocked +
                ", type: "  + type +
                " }";
    }


}
