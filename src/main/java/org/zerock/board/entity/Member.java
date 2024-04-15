package org.zerock.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity //테이블을 자동으로 관리
@Getter
@Builder
@AllArgsConstructor //Builder 패턴 필수
@NoArgsConstructor //Builder 패턴 필수
@ToString
public class Member extends BaseEntity{

    @Id //pk 설정
    private String email;
    private String password;
    private String name;
}
