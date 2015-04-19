package br.com.amigo.secreto.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport{ 
	
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
            converters.add(converter);
    }
}
