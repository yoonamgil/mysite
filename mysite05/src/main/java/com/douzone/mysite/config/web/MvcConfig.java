package com.douzone.mysite.config.web;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {
	//View Resolber
	@Bean
	public ViewResolver  viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setExposeContextBeansAsAttributes(true);
		

		return viewResolver;
	}
	//Default Servlet Handler 
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	//Mvc Resouces(URL Magic Mapping)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/**").addResourceLocations("file:/mysite-uploads/");
	}
	
	//Message Converter
		public StringHttpMessageConverter stringHttpMessageConverter() {
			StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
			
					
			messageConverter.
			setSupportedMediaTypes(
					Arrays.asList(
							new MediaType("text","html",Charset.forName("utf-8"))
							
					)
			);
			return messageConverter;
		}
		
		public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
			Jackson2ObjectMapperBuilder builder= 
					new Jackson2ObjectMapperBuilder()
					.indentOutput(true)
					.dateFormat(new SimpleDateFormat("yyyy-mm-dd"));
			
			MappingJackson2HttpMessageConverter messageConverter = 
					new MappingJackson2HttpMessageConverter(builder.build());
			
			messageConverter.
			setSupportedMediaTypes(
					Arrays.asList(
							new MediaType("application","json",Charset.forName("utf-8"))
							
					)
			);
			return messageConverter;
		}
		@Override
		public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
			
			converters.add(stringHttpMessageConverter());
			converters.add(mappingJackson2HttpMessageConverter());
		}
		
	
	
	
}
