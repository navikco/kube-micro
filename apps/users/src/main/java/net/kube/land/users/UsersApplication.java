package net.kube.land.users;

import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(scanBasePackages = {"net.kube.land"})
public class UsersApplication {

    @Autowired(required = false)
    private EurekaClient eurekaClient;

    @Autowired
    private Environment environment;

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersApplication.class);

    public static void main(String[] args) {

        LOGGER.info(System.getProperty("spring.application.name") + " ::: Spring.Profiles.Active :-> " + System.getProperty("spring.profiles.active"));

        SpringApplication.run(UsersApplication.class, args);

        LOGGER.info("Started Users Microservice...");
    }

    @Configuration
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                    .csrf().disable();
        }
    }

    @PreDestroy
    public void onDestroy() {

        LOGGER.info("DESTROYING :::>>> " + environment.getProperty("spring.application.name") + " ::: Spring.Profiles.Active :-> " +environment.getProperty("spring.profiles.active"));

        if(eurekaClient != null)
        {
            try {
                eurekaClient.shutdown();
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                LOGGER.error("OnDestroy() Failed!!!", e);
            }
        }

        LOGGER.info("DESTROYED :::>>> " + environment.getProperty("spring.application.name") + " ::: Spring.Profiles.Active :-> " + environment.getProperty("spring.profiles.active"));
    }
}
