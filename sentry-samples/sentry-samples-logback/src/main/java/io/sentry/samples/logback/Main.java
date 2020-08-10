package io.sentry.samples.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main {
  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    LOGGER.info("Hello Sentry!");

    MDC.put("userId", UUID.randomUUID().toString());

    LOGGER.info("User has made a purchase of product: {}", 445);
    LOGGER.info("Trying out list: {}", Arrays.asList("foo", "bar"));
    Map<String, Object> map = new HashMap<>();
    map.put("foo", "bar");
    map.put("id", 12);
    LOGGER.info("Trying out map: {}", map);
    LOGGER.info("Trying out object: {}", new Person("maciej"));

    try {
      throw new RuntimeException("Invalid productId=33");
    } catch (Exception e) {
      LOGGER.error("Something went wrong", e);
    }
  }
}

class Person {
  private final String name;

  public Person(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Person{" +
      "name='" + name + '\'' +
      '}';
  }
}
