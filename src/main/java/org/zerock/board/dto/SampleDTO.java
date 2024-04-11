package org.zerock.board.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data //getter.setter.toString....등
@Builder(toBuilder = true) //빌더 패턴
public class SampleDTO {
    //dto는 프론트에서 자바까지 객체를 담당함.
    //entity는 db에서 자바까지 영속성을 담당함.
    //나중에는 dtotoentity, entitytodto라는 메서드가 이 2개를 전이하는 역할을 진행함.
    private Long sno;
    private String first;
    private String last;
    private LocalDateTime regTime;
}
