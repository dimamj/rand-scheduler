package ru.randscheduler.data.json_converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by dimaMJ on 15.03.2018
 */
@JsonComponent
public class LocalDateJsonConverter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static class Serializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            String dateString = localDate.format(formatter);
            jsonGenerator.writeString(dateString);
        }
    }

    public static class Deserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonNode node = jp.readValueAsTree();
            String dateString = node.textValue();
            return LocalDate.parse(dateString, formatter);
        }
    }
}
