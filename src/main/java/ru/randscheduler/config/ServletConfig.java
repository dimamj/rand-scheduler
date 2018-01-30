package ru.randscheduler.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.randscheduler.web.filters.LoginFilter;

/**
 * Created by dimaMJ on 29.01.2018
 */
@Configuration
@ServletComponentScan(basePackageClasses = LoginFilter.class)
public class ServletConfig {
}
