package app.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Component
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(1800).allowedOrigins("*");
//                .allowedOrigins("http://localhost:8848","http://127.0.0.1:8848","http://127.0.0.1:8848",
//                        "http://localhost:8888","http://111.229.135.141:8888","http://hmj.mbg17.cn:8888",
//                        "http://192.168.3.59:8848","http:www.haomingjian.top:8888","http://192.168.3.69:8848");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login","/user/register");
    }


//    public void addViewControllers(ViewControllerRegistry registry) {
////        做路径映射
//        registry.addViewController("/").setViewName("index");
//
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
