package com.takeout.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class emailIdentiConfig {
    @Bean
    public Producer emailCodeProducer(){
        DefaultKaptcha kaptcha = new DefaultKaptcha();

        Properties properties = new Properties();
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
        properties.setProperty("kaptcha.textproducer.char.length", "6");

        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
