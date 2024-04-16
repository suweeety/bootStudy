package org.zerock.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //테이블을 자동으로 관리
@Getter
@Builder
@AllArgsConstructor //Builder 패턴 필수
@NoArgsConstructor //Builder 패턴 필수
@ToString(exclude = "movie") //연관 관계 주의
public class MovieImage {
    //단방향 참조로 처리
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //마리아db용 자동번호
    private Long inum;

    private String uuid; //고유한 번호 생성 후 사용

    private String imgName;

    private String path; //이미지 저장 경로(년/월/일 폴더구조)

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie; //FK

}
