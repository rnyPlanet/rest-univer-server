package com.example.demo.model.user;

import com.example.demo.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "photos")
@Data
public class Photo extends BaseEntity implements Serializable {

    @Column(name = "file_path")
    private String filename;

    @JsonIgnore
    @Column(name = "id_user_creator")
    private Long userId;

    @Override
    public String toString() {
        return "Photo { filename: " + filename +
                ", userId: " + userId +
                " }";
    }

}
