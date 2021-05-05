package com.dy.common.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


/**
 * 验证码配置
 * @author cxj
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer(){
        Properties properties = new Properties();
        properties.put(Constants.KAPTCHA_BORDER,"no");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR,"black");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE,"5");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING,"0123456789");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return  defaultKaptcha;
    }
}
