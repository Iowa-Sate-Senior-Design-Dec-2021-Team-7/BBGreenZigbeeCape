package webhost.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.socket.server.standard.ServerEndpointRegistration;

/**
 * {@code webhost} {@code Bean} configurator
 */
@Configuration
public class Configurator extends ServerEndpointRegistration.Configurator implements ApplicationContextAware {
    
    private static volatile BeanFactory context;
    
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
    
    @Override
    public <T> T getEndpointInstance(Class<T> endpoint) {
        return context.getBean(endpoint);
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Configurator.context = applicationContext;
    }
}
