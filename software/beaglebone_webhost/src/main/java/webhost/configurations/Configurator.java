package webhost.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * {@code webhost} {@code Bean} configurator
 */
@Configuration
public class Configurator {
    
    /**
     * {@code Bean} used to get the {@code ObjectMapper} to use at runtime
     * @return
     *      The {@code ObjectMapper} methods this application should use when writing {@code Objects} to {@code JSON Strings}, vice a versa
     * @see com.fasterxml.jackson.databind.ObjectMapper
     */
    @Bean
    @Primary
    public ObjectMapper config_objectMapper() {
    
        return new ObjectMapper()
                       .registerModule(new ParameterNamesModule())
                       .registerModule(new Jdk8Module())
                       .registerModule(new JavaTimeModule()); // new module, NOT JSR310Module
    }
}
