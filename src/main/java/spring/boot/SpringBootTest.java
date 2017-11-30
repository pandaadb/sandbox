package spring.boot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

@EnableAutoConfiguration
@Configuration
@PropertySource(value = { "classpath:/properties/config.default.json" }, factory=SpringBootTest.JsonLoader.class )
public class SpringBootTest extends SpringBootServletInitializer {

    @Bean
    public Object test(Environment e) {
        System.out.println(e.getProperty("test"));
        return new Object();
    }
    
    
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTest.class);
    }
    
    public static class JsonLoader implements PropertySourceFactory {

        @Override
        public org.springframework.core.env.PropertySource<?> createPropertySource(String name,
                EncodedResource resource) throws IOException {
            Map readValue = new ObjectMapper().readValue(resource.getInputStream(), Map.class);
            return new MapPropertySource("json-source", readValue);
        }
        
    }
}
