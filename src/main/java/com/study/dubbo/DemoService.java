package com.study.dubbo;

import org.springframework.stereotype.Component;

@Component
public interface DemoService {

	String sayHello(String name);
}
