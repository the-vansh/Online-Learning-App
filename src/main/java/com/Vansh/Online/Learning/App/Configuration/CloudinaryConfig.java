package com.Vansh.Online.Learning.App.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "djoatyv4p", // Replace with your Cloud name
                "api_key", "112849827557621",
                "api_secret", "YYE6qXSl4jQZAl2eP7cgj1cDLo4"
        ));
    }
}