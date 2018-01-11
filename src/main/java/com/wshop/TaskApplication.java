package com.wshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by lujun.chen on 2017/3/10.
 */
//@EnableScheduling
@SpringBootApplication
public class TaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskApplication.class,args);
  }

}
