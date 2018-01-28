package service;

import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * Created by Jeremy on 18/01/2018.
 */
@SpringBootApplication
public class ObserveurApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ObserveurApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ObserveurApp.class, args);
    }

   /* @Bean
    public FilterRegistrationBean remoteAddressFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        RemoteAddrFilter filter = new RemoteAddrFilter();
        filter.setAllow("127.0.0.1");
        //filter.setAllow("0:0:0:0:0:0:0:1");
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }*/
}
