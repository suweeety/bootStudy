package org.zerock.board.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.dto.SampleDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@RestController //기본이 json으로 처리한다.
@Controller
@RequestMapping("/sample") //http://localhost/sample/???
@Log4j2
public class SampleController {
    @GetMapping("/hello")
    public String[] hello() {
        return new String[]{"Hello","World"};
    }

    @GetMapping("/ex1") //http://localhost/sample/ex1.html -> void는 같은 경로와 파일.html
    public void ex1() {
        log.info("ex1 메서드 실행");
        // resources/templetes/sample/ex1.html
    }

    //db를 안쓰고 사용
    @GetMapping("/ex2") //http://localhost/sample/ex2.html
    public void exModel(Model model) {
        //Spring은 Model 타입으로 모든 객체나 데이터를 가지고 있다.
        List<SampleDTO> list = IntStream.rangeClosed(1,20)
                .asLongStream().mapToObj(i -> {
                    SampleDTO dto = SampleDTO.builder()
                            .sno(i)
                            .first("첫번째 필드..." + i)
                            .last("마지막 필드..." + i)
                            .regTime(LocalDateTime.now())
                            .build(); //빌더 패턴을 이용해 값을 리스트로 만듦
                    return dto;
                }).collect(Collectors.toList()); //리스트 완성
        //모델에 여러객체를 추가할 때 model.addAllAttributes();
        model.addAttribute("list", list);
        //프론트에서 list를 호출하면 list 객체가 나온다.
    }

    @GetMapping({"/exInline"})
    public String exInline(RedirectAttributes redirectAttributes) {
        log.info("exInline......");

        SampleDTO dto = SampleDTO.builder()
                .sno(100L)
                .first("First..100")
                .last("Last..100")
                .regTime(LocalDateTime.now())
                .build();
        redirectAttributes.addFlashAttribute("result", "success");
        redirectAttributes.addFlashAttribute("dto", dto);

        return "redirect:/sample/ex3";
    }

    @GetMapping("/ex3")
    public void ex3() {
        log.info("ex3");
    }

}
