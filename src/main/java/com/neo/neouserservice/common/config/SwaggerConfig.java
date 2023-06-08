package com.neo.neouserservice.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title(ApplicationInfoUtil.TITLE)
                .description(ApplicationInfoUtil.DESCRIPTION)
                .version(ApplicationInfoUtil.VERSION)
                .contact(ApplicationInfoUtil.CONTACT)
                .license(ApplicationInfoUtil.LICENSE));
    }

    @UtilityClass
    private static class ApplicationInfoUtil {

        public static final String TITLE = "neo-user-service";
        public static final String DESCRIPTION = "The neo-user-service is a RESTful web service that provides user management functionality";
        public static final String VERSION = "0.0.1-SNAPSHOT";

        private static final String CONTACT_NAME = "Mohammad-Masoomi-Homayoun";
        private static final String CONTACT_EMAIL = "en.masoomi@gmail.com";
        private static final String CONTACT_URL = "";

        public static final Contact CONTACT = new Contact().name(CONTACT_NAME).email(CONTACT_EMAIL).url(CONTACT_URL);

        private static final String LICENSE_NAME = "MIT";
        private static final String LICENSE_URL = "https://opensource.org/license/mit/";

        public static final License LICENSE = new License().name(LICENSE_NAME).url(LICENSE_URL);
    }

}
