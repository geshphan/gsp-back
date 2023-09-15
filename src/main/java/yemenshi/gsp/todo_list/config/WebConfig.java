package yemenshi.gsp.todo_list.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import yemenshi.gsp.todo_list.interceptor.AuthnInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthnInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/member/login", "/swagger-ui/index.html");
    }
}
