package com.github.rvenancio.springboot.otelbaggage;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author rvenancio
 */
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(DemoApplication.class)
                .run(args);
    }
}
