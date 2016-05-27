package com.autodomum.aplicacao.config;

import com.autodomum.aplicacao.config.serializers.LocalDateDeserializer;
import com.autodomum.aplicacao.config.serializers.LocalDateSerializer;
import com.autodomum.aplicacao.config.serializers.LocalDateTimeDeserializer;
import com.autodomum.aplicacao.config.serializers.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author sabrina on 17/05/16.
 */
@Configuration
public class SerializersConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.serializerByType(LocalDate.class, new LocalDateSerializer());
        builder.deserializerByType(LocalDate.class, new LocalDateDeserializer());
        builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer());
        builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());
        return builder;
    }

}
