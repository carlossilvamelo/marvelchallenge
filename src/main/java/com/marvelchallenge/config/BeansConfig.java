package com.marvelchallenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class BeansConfig {

    @Value("${messages.exception:classpath:/messages/global}")
    private String messagesResource;

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MessageSource messageSource() {
        var messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(messagesResource.split(";"));
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
