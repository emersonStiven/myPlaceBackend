package emerson.sample.myPlace.configuration;

import emerson.sample.myPlace.DataBaseEntities.GeographyInfoView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
@ComponentScan(basePackages = "emerson.sample.myPlace")
@EnableAutoConfiguration
//@EnableJpaRepositories(basePackages = "emerson.sample.myPlace.repositoryLayer")
//@EntityScan(basePackages = "emerson.sample.myPlace")
public class ConfigApplication {

    @Bean
    public ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }





}
