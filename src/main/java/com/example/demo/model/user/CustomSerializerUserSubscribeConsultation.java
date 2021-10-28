package com.example.demo.model.user;

import com.example.demo.dto.UserSubscribeConsultationDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class CustomSerializerUserSubscribeConsultation extends StdSerializer<Collection<User>> {

    public CustomSerializerUserSubscribeConsultation() {
        this(null);
    }

    public CustomSerializerUserSubscribeConsultation(Class<Collection<User>> u) {
        super(u);
    }

    @Override
    public void serialize(Collection<User> u, JsonGenerator jg, SerializerProvider sp) throws IOException {
        List<UserSubscribeConsultationDto> users = UserSubscribeConsultationDto.fromUser(u);
        jg.writeObject(users);
    }


}
