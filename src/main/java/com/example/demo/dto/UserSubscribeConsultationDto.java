package com.example.demo.dto;

import com.example.demo.model.user.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserSubscribeConsultationDto extends UserDto {

    public static List<UserSubscribeConsultationDto> fromUser(Collection<User> us) {
        List<UserSubscribeConsultationDto> list = new ArrayList();

        for (User user : us) {
            UserSubscribeConsultationDto userDto = new UserSubscribeConsultationDto();
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
            list.add(userDto);
        }

        return list;
    }
}
