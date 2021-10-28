package com.example.demo.model.consultation;

import com.example.demo.dto.UserDto;
import com.example.demo.model.user.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomSerializer extends StdSerializer<User> {

    public CustomSerializer() {
        this(null);
    }

    public CustomSerializer(Class<User> u) {
        super(u);
    }

    @Override
    public void serialize(User u, JsonGenerator jg, SerializerProvider sp) throws IOException {
        UserDto userDto = UserDto.fromUser(u);
        jg.writeObject(userDto);
    }

}
