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
@Table(name = "subject_cource")
@Data
public class SubjectCource extends BaseEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "short_name")
    private String shortName;

    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<Classes> classes;

    @Override
    public String toString() {
        return "SubjectCource { fullName: " + fullName +
                ", shortName: " + shortName +
                " } ";
    }

}
