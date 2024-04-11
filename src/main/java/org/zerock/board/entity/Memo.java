package org.zerock.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //해당 클래스가 엔티티 역할 담당
@Table(name = "tbl_memo") //db 테이블 명을 지정
@ToString //객체를 문자열로 변경
@Getter
@Builder //메서드, 필드(값), 필드(값), builder; (빌더 패턴 - @AllArgsConstructor, @NoArgsConstructor 필수)
@AllArgsConstructor //모든 매개값을 갖는 생성자 / new 클래스(모든 필드값 파라미터로 만듦)
@NoArgsConstructor //매개값이 없는 생성자 / new 클래스();
public class Memo {
    //엔티티는 데이터베이스의 테이블과 필드를 생성시켜 관리하는 객체
    //엔티티를 이용해서 JPA를 활성화 하려면 application.properties 에 필수 항목 추가
    @Id //기본키를 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;



}
