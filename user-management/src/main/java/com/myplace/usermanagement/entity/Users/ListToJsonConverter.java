package com.myplace.usermanagement.entity.Users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class ListToJsonConverter implements AttributeConverter<Set<String>, String> {

    private final ObjectMapper mapper;

    @Override
    public String convertToDatabaseColumn(Set<String> attribute){
        String value = "";
        try {
            value = mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData){
        Set<String> list = new HashSet<>();
        try {
            list = mapper.readValue(dbData, new TypeReference<Set<String>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
