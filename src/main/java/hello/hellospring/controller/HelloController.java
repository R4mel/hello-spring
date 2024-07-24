package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // localhost:8080/hello-mvc?name=spring
        model.addAttribute("name", name); // key, value
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http의 body에 문자 내용을 직접 반환
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody // 객체가 오면 JSON 방식으로 데이터를 만들어서 반환한다.
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    /*
    객체는 JSON으로 반환해준다.
    1. 정적 컨텐츠
    2. 템플릿 엔진을 MVC 방식으로 쪼개서 렌더링된 HTML을 전달해준다.
    3. API 방식: 객체를 반환
     */
    
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /*
    컨트롤러: 웹 MVC의 컨트롤러 역할
    서비스: 핵심 비즈니스 로직 구현
    리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
    도메인: 비즈니스 도메인 객체, 예)회원, 주문, 쿠폰 등등 주로 DB에 저장하고 관리됨
     */
}