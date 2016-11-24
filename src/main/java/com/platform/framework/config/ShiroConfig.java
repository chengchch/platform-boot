package com.platform.framework.config;

import com.platform.framework.security.FormAuthenticationFilter;
import com.platform.framework.security.KickoutSessionControlFilter;
import com.platform.framework.security.SecurityRealm;
import com.platform.framework.security.shiro.cache.JedisCacheManager;
import com.platform.framework.security.shiro.session.JedisSessionDAO;
import com.platform.framework.security.shiro.session.SessionIdGen;
import com.platform.framework.security.shiro.session.SessionManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lufengc
 * @date 2016/11/10
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private RedisConfig redisConfig;
    @Autowired
    private SystemProperties systemProperties;
    @Autowired
    private SecurityRealm securityRealm;
    @Autowired
    private FormAuthenticationFilter formAuthenticationFilter;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.addUrlPatterns("/*");// 可以自己灵活的定义很多，避免一些根本不需要被Shiro处理的请求被包含进来
        return filterRegistration;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager());
        //factoryBean.setLoginUrl("${cas.server.url}?service=${cas.project.url}/cas");
        factoryBean.setLoginUrl("/a/login");
        factoryBean.setSuccessUrl("/a?login");

        Map<String, Filter> filters = new LinkedHashMap<>();
        CasFilter casFilter = new CasFilter();
        casFilter.setFailureUrl("/a/login");
        filters.put("cas", casFilter);
        filters.put("authc", formAuthenticationFilter);
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager(cacheManager());
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(2);
        kickoutSessionControlFilter.setKickoutUrl("/a/login?kickout=1");
        filters.put("kickout", kickoutSessionControlFilter);
        factoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/uploads/**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/api/**", "anon");
        filterChainDefinitionMap.put("/f/**", "anon");
        filterChainDefinitionMap.put("/a/login", "authc");
        filterChainDefinitionMap.put("/a/logout", "logout");
        filterChainDefinitionMap.put("/a/**", "kickout,user");
        filterChainDefinitionMap.put("/a/cas", "cas");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(securityRealm);
        securityManager.setCacheManager(cacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public JedisCacheManager cacheManager() {
        JedisCacheManager cacheManager = new JedisCacheManager();
        cacheManager.setCacheKeyPrefix(redisConfig.getKeyPrefix() + "_shiro_cache_");
        return cacheManager;
    }

    @Bean
    public SessionManager sessionManager() {
        SessionManager sessionManager = new SessionManager();
        sessionManager.setSessionDAO(sessionDao());
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationInterval(120000);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(new SimpleCookie(redisConfig.getKeyPrefix() + ".session.id"));
        return sessionManager;
    }

    @Bean
    public JedisSessionDAO sessionDao() {
        JedisSessionDAO sessionDAO = new JedisSessionDAO();
        sessionDAO.setSessionIdGenerator(new SessionIdGen());
        sessionDAO.setSessionKeyPrefix(redisConfig.getKeyPrefix() + "_shiro_session_");
        return sessionDAO;
    }

    /**
     * AOP式方法级权限检查
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager());
        return aasa;
    }

}
