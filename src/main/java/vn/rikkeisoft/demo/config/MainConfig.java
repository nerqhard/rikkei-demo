package vn.rikkeisoft.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Thiet lap cau hinh cho messages.propertise
@Configuration
public class MainConfig implements WebMvcConfigurer {

    @Bean(name = "messageSource")
    public MessageSource getMessageResource() {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
        messageResource.setBasename("classpath:messages/messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

}
