package com.smhrd.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@SpringBootApplication : 위치가 중요**
//이 클래스의 위치를 기준으로 하위에 있는 설정 내용들을 읽어줌
// -> 이 클래스의 위치는 프로젝트의 최상단에 위치해야 함
// 스프링 부트의 설정 자동화, Bean(객체) 읽기와 생성 하는 작업을 자동으로 설정

@SpringBootApplication
public class BootBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootBasicApplication.class, args);
	}

}
