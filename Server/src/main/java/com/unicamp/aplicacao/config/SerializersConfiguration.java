package com.unicamp.aplicacao.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.unicamp.aplicacao.config.serializers.LocalDateDeserializer;
import com.unicamp.aplicacao.config.serializers.LocalDateSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;

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
        return builder;
    }

}
