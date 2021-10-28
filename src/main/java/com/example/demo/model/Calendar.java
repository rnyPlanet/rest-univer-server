package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "calendar_test")
@Data
public class Calendar extends BaseEntity {

    @Temporal(TemporalType.DATE)
    @Column(name = "day")
    private Date day;

    @Column(name = "t_date")
    private String tDate;

    @Column(name = "week")
    private String week;

    @Override
    public String toString() {
        return "Calendar { day: " + day +
                ", tDate: " + tDate +
                ", week: " + week +
                " }";
    }
}
