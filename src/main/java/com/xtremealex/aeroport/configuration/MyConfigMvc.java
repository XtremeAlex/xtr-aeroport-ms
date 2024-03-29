package com.xtremealex.aeroport.configuration;


import com.xtremealex.aeroport.interceptors.MyLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class MyConfigMvc implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("index.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        if (!registry.hasMappingForPattern("/resources/**")) {
            registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");
        }
    }

    //http://localhost:8080/xtr-aeroport/v3/swagger-ui/index.html
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        CorsRegistration corsRegistration = registry.addMapping("*");
        corsRegistration.allowedOrigins("*");
        corsRegistration.allowedHeaders("*");
        corsRegistration.allowedMethods("GET, POST, PUT, DELETE, OPTIONS");
        corsRegistration.allowCredentials(false);
        corsRegistration.exposedHeaders("*");
        corsRegistration.maxAge(0L);
    }

    //Registrazione dell'interceptor affinché sia effettivamente invocato per ogni richiesta.
    @Bean
    public MyLoggingInterceptor myloggingInterceptor() {
       return new MyLoggingInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(myloggingInterceptor());
        interceptorRegistration.addPathPatterns("/*").excludePathPatterns("/css/**", "/js/**", "/v3/**");
    }
}
