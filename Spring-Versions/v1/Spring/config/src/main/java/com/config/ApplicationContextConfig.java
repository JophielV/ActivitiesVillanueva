package com.config;

import org.springframework.context.annotation.*;
import com.service.BusinessLogicService;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.controller")
public class ApplicationContextConfig {


   @Bean(name = "service")
   public BusinessLogicService getService(){
      return new BusinessLogicService("root","sample");
   }

   @Bean(name = "viewResolver")
   public InternalResourceViewResolver getViewResolver() {
     InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
     viewResolver.setPrefix("/WEB-INF/views/");
     viewResolver.setSuffix(".jsp");
     return viewResolver;
   }
}
