package org.zerock.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //테이블을 자동으로 관리
@Getter
@Builder
@AllArgsConstructor //Builder 패턴 필수
@NoArgsConstructor //Builder 패턴 필수
@ToString
@Table(name = "m_member")
public class Member extends BaseEntity{

    @Id //pk 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    private String email;
    private String password;
    private String name;
}
