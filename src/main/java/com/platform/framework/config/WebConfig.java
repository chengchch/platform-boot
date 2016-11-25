package com.platform.framework.config;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
import com.platform.framework.common.SysConfigManager;
import com.platform.framework.intercepter.LogInterceptor;
import com.platform.framework.intercepter.MobileIntercepter;
import com.platform.framework.servlet.ValidateCodeServlet;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author lufengc
 * @date 2016/11/10
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${adminPath}")
    private String adminPath;

    @Autowired
    private LogInterceptor logInterceptor;

    @Autowired
    private SystemProperties systemProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                .addPathPatterns(adminPath + "/**")
                .excludePathPatterns(adminPath + "/")
                .excludePathPatterns(adminPath + "/login")
                .excludePathPatterns(adminPath + "/home");
        registry.addInterceptor(new MobileIntercepter()).addPathPatterns(adminPath + "/**");
        super.addInterceptors(registry);
    }

    @Bean
    public FilterRegistrationBean siteMeshFilter() {
        FilterRegistrationBean fitler = new FilterRegistrationBean();
        fitler.setFilter(new SiteMeshFilter());
        fitler.addUrlPatterns("/a/*", "/f/*");
        return fitler;
    }

    @Bean
    public ServletRegistrationBean validateCodeServletReg() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new ValidateCodeServlet());
        reg.addUrlMappings("/servlet/validateCodeServlet");
        return reg;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setProviderClass(HibernateValidator.class);
        validatorFactoryBean.setValidationMessageSource(messageSource());
        return validatorFactoryBean;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("org/hibernate/validator/ValidationMessages");
        messageSource.setUseCodeAsDefaultMessage(false);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(60);
        return messageSource;
    }

    @Bean
    public SysConfigManager sysConfigManager() {
        SysConfigManager sysConfigManager = new SysConfigManager();
        sysConfigManager.setSystemProperties(systemProperties);
        return sysConfigManager;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * 该方法导致属性注入值为null
     */
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }

}