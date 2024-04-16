package org.zerock.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity //테이블을 자동으로 관리
@Getter
@Builder
@AllArgsConstructor //Builder 패턴 필수
@NoArgsConstructor //Builder 패턴 필수
@ToString //객체가 아닌 문자열로 변환
public class Movie extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //마리아db용 자동번호
    private Long mno;

    private String title; //영화제목

}
