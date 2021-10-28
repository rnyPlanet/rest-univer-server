package com.example.demo.model.schedule;

import com.example.demo.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "lesson_time")
@Data
public class LessonTime extends BaseEntity {

    @Column(name = "position")
    private int position;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @JsonIgnore
    @OneToMany(mappedBy = "positionInDay")
    private List<Classes> classes;

    @Override
    public String toString() {
        return "LessonTime { position: " + position +
                ", startTime: " + startTime +
                ", endTime: " + endTime +
                " }";
    }

}
