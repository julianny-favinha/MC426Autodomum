package com.autodomum.aplicacao.config;

import com.autodomum.aplicacao.queue.AutodomumQueue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sabrina on 19/06/16.
 */
@Configuration
public class QueueConfiguration {

    @Bean
    @Qualifier("casaQueue")
    public AutodomumQueue casaQueue() {
        return new AutodomumQueue();
    }

    @Bean
    @Qualifier("audioQueue")
    public AutodomumQueue audioQueue() {
        return new AutodomumQueue();
    }

}
