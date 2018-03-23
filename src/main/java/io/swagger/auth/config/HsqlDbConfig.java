package io.swagger.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.Servlet;
import java.util.HashMap;
import java.util.Map;

/**
 * Stub out test data when running in unit only.
 */
@Configuration
public class HsqlDbConfig {

    private static final Logger LOG = LoggerFactory.getLogger(HsqlDbConfig.class);

    /**
     * Bind the HSQLDB Servlet mode to allow external database tools to connect to and view
     * the in-memory database.
     */
    @Bean
    ServletRegistrationBean bindHsqldbServlet() {
        try {
            ServletRegistrationBean s = new ServletRegistrationBean((Servlet) Class.forName("org.hsqldb.server.Servlet").newInstance(), "/hsqldb/*");
            Map<String, String> initParameters = new HashMap<>();
            initParameters.put("hsqldb.server.database", "mem:testdb");
            s.setInitParameters(initParameters);
            return s;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}