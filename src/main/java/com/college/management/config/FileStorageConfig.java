package com.college.management.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import jakarta.annotation.PostConstruct;
import java.io.File;
@Configuration
public class FileStorageConfig implements WebMvcConfigurer {
    private static final Logger log = LoggerFactory.getLogger(FileStorageConfig.class);
    @Value("${app.upload.dir}") private String uploadDir;
    @PostConstruct public void init() {
        File dir = new File(uploadDir);
        if (!dir.exists()) { boolean created = dir.mkdirs(); log.info("Upload dir created: {}", created); }
    }
    @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:" + uploadDir);
    }
}
