package org.zerock.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //테이블을 자동으로 관리
@Getter
@Builder
@AllArgsConstructor //Builder 패턴 필수
@NoArgsConstructor //Builder 패턴 필수
@ToString(exclude = {"movie", "member"}) //연관 관계 주의
public class Review extends BaseEntity{
    //양방향 참조로 처리
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //마리아db용 자동번호
    private Long reviewnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie; //FK

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member; //FK

    private int grade;

    private String text;
}
