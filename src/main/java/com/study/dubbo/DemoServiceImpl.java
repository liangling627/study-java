package com.study.dubbo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		return "hello :" + name;
	}

}
