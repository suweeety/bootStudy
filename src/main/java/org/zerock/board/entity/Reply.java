package org.zerock.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //테이블을 자동으로 관리
@Getter
@Builder
@AllArgsConstructor //Builder 패턴 필수
@NoArgsConstructor //Builder 패턴 필수
@ToString(exclude = "board") //객체가 아닌 문자열로 변환
public class Reply extends BaseEntity{
    //extends BaseEntity 를 상속 받아서 날짜 시간 자동처리

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //마리아db용 자동번호
    private Long rno;
    private String text;
    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board; //연관관계 지정

}
