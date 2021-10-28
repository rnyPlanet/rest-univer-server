package com.example.demo.dto;


import com.example.demo.model.user.Photo;
import com.example.demo.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String patronymic;
    private String firstName;
    private String lastName;
    private String lastNameInternational;
    private String firstNameInternational;
    private String patronymicNameInternational;
    private String email;
    private Photo photo;
    private String telefon1;
    private String telefon2;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPatronymic(patronymic);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLastNameInternational(lastNameInternational);
        user.setFirstNameInternational(firstNameInternational);
        user.setPatronymicNameInternational(patronymicNameInternational);
        user.setEmail(email);
        user.setPhoto(photo);
        user.setTelephone_1(telefon1);
        user.setTelephone_2(telefon2);

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPatronymic(user.getPatronymic());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setLastNameInternational(user.getLastNameInternational());
        userDto.setFirstNameInternational(user.getFirstNameInternational());
        userDto.setPatronymicNameInternational(user.getPatronymicNameInternational());
        userDto.setEmail(user.getEmail());
        userDto.setPhoto(user.getPhoto());
        userDto.setTelefon1(user.getTelephone_1());
        userDto.setTelefon2(user.getTelephone_2());

        return userDto;
    }
}

