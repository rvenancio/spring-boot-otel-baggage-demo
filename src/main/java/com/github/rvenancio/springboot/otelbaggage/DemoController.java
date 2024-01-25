package com.github.rvenancio.springboot.otelbaggage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rvenancio
 */
@RestController
public class DemoController {
    private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);

    @GetMapping(value = "/")
    public ResponseEntity<String> index() {
        LOG.info("### DemoController start ###");

        LOG.info("Controller Baggage: {}", OtelUtils.getAll());

        LOG.info("### DemoController end ###");

        return new ResponseEntity<>("Greetings from Spring Boot with Undertow!", HttpStatus.OK);
    }
}
