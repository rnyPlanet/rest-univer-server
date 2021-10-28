package com.example.demo.model.schedule;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.SubGroup;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
@Data
public class Classes extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "id_subject")
    private SubjectCource subject;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private TypeClasses type;

    public enum Place {POOL, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY}

    @Column(name = "place")
    @Enumerated(EnumType.STRING)
    private Place place;

    @ManyToOne
    @JoinColumn(name = "position_in_day")
    private LessonTime positionInDay;

    @Column(name = "additional_requirements")
    private String additionalRequirements;

    public enum Week {FIRST, SECOND, BOTH}

    @Column(name = "week")
    @Enumerated(EnumType.STRING)
    private Week week;

    @JoinColumn(name = "id_group", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Group group;

    @JoinColumn(name = "id_professor", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Professor professor;

    @JoinColumn(name = "id_room", referencedColumnName = "ID")
    @ManyToOne
    private Room room;

    @Column(name = "subgroup")
    @Enumerated(EnumType.STRING)
    private SubGroup subgroup;

    @Override
    public String toString() {
        return "Classes { subject: " + subject +
                ", type: " + type +
                ", place: "  + place +
                ", positionInDay: " + positionInDay +
                ", additionalRequirements: " + additionalRequirements +
                ", week: " + week +
                ", group: " + group +
                ", professor: " + professor +
                ", room: " + room +
                ", subgroup: " + subgroup +
                " }";
    }

}
