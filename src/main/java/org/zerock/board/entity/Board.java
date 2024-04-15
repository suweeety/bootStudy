package org.zerock.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //테이블을 자동으로 관리
@Getter
@Builder
@AllArgsConstructor //Builder 패턴 필수
@NoArgsConstructor //Builder 패턴 필수
@ToString(exclude = "writer") //@ToString 은 항상 exclude
public class Board extends BaseEntity{
    //extends BaseEntity 를 상속 받아서 날짜 시간 자동처리
    @Id //pk지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //마리아db용 자동번호
    private Long bno;
    private String title;
    private String content;

    @ManyToOne (fetch = FetchType.LAZY) //명시적으로 Lazy 로딩 지정
    private Member writer; //연관관계 지정

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

}
