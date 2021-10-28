package com.example.demo.model.user;

import com.example.demo.dto.UserCreatedConsultationDto;
import com.example.demo.model.consultation.Consultation;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.List;

public class CustomSerializerUserCreatedConsultation extends StdSerializer<List<Consultation>> {

    public CustomSerializerUserCreatedConsultation() {
        this(null);
    }

    public CustomSerializerUserCreatedConsultation(Class<List<Consultation>> u) {
        super(u);
    }

    @Override
    public void serialize(List<Consultation> t, JsonGenerator jg, SerializerProvider sp) throws IOException {
        List<UserCreatedConsultationDto> list = UserCreatedConsultationDto.fromConsultation(t);
        jg.writeObject(list);
    }

}
