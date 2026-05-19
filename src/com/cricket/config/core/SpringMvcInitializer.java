package com.cricket.config.core;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.cricket.config.WebMvcConfig;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;

public class SpringMvcInitializer extends
 AbstractAnnotationConfigDispatcherServletInitializer {

 @Override
 protected Class<?>[] getRootConfigClasses() {
 
 return new Class[] { WebMvcConfig.class };
 }

 @Override
 protected Class<?>[] getServletConfigClasses() {
 
 return null;
 }

 @Override
 protected String[] getServletMappings() {
 
 return new String[] { "/" };
 }

 @Override
 protected void customizeRegistration(ServletRegistration.Dynamic registration) {
     registration.setMultipartConfig(
         new MultipartConfigElement("", -1L, -1L, 0)
     );
 }
}