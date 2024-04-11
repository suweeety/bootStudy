package org.zerock.board.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController // 기본이 json으로 처리한다.
@Controller
@RequestMapping("/sample") // http://localhost/sample/?????
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public String[] hello(){
        return new String[]{"Hello", "World"};
    }

    @GetMapping("/ex1") // http://localhost/sample/ex1.html -> void는 같은 경로와 파일.html
    public void ex1() {
        log.info("ex1 메서드 실행.......");
        // resources/templates/sample/ex1.html 에 파일 필수
    }



}

