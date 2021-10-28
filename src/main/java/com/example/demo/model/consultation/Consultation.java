package com.example.demo.model.consultation;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.schedule.Room;
import com.example.demo.model.user.CustomSerializerUserSubscribeConsultation;
import com.example.demo.model.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "consultations")
@Data
public class Consultation extends BaseEntity {

    @JsonSerialize(using = CustomSerializer.class)
    @JoinColumn(name = "id_user_creator", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User createdUser;

    @JoinColumn(name = "id_room", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Room room;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    @Column(name = "date_of_passage")
    private Date dateOfPassage;

    @JsonSerialize(using = CustomSerializerUserSubscribeConsultation.class)
    @JoinTable(name = "consultation_users", joinColumns = {
            @JoinColumn(name = "id_consultation", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "id_user", referencedColumnName = "id")})
    @ManyToMany
    private Collection<User> usersCollection;

    @Column(name = "description")
    private String description;

    @Override
    public String toString() {
        return "Consultation { room: " + room +
                ", dateOfPassage: " + dateOfPassage +
                ", description: " + description +
                " }";
    }
}
