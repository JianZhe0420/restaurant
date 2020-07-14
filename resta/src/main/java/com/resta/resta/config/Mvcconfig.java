//package com.resta.resta.config;
//
//import com.resta.resta.LoginHandlerInterceptor.LoginHandlerInterceptor;
//import org.aopalliance.intercept.Interceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@EnableWebMvc
//@SpringBootConfiguration
//public class Mvcconfig extends WebMvcConfigurerAdapter {
//
//
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
//    }
//
//    @Bean
//    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
//        WebMvcConfigurerAdapter adapter =new WebMvcConfigurerAdapter() {
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry){
//                registry.addViewController("/").setViewName("login");
//                registry.addViewController("/index.html").setViewName("login");
//                registry.addViewController("/re/index.html").setViewName("login");
//
//            }
////            public void addInterceptors(InterceptorRegistry registry){
////                registry.addInterceptor((HandlerInterceptor) new LoginHandlerInterceptor()).addPathPatterns("/**")
////                        .excludePathPatterns("/","/index.html","/re/index.html","/re/login");
////        }
//
//        };
//        return adapter;
//
//    }
//
//}
