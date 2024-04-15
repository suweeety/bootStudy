package org.zerock.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass //테이블로 생성되지 않도록 하는 어노테이션! 실제 테이블은 BaseEntity클래스를 상속한 엔티티의 클래스로 테이블이 생성됨
@Getter //getter
@EntityListeners(value = {AuditingEntityListener.class}) //AuditingEntityListener 는 JPA내부에서 엔티티 객체가 생성/변경되는 것을 감지하는 역할! setter 대신 감시용 코드(데이터 변경 감지하여 적용) -> GuestbookApplication 에 추가코드 필수로 넣어야함!
abstract class BaseEntity { //abstract 상속간에 추상클래스 동작
    //테이블이 공통되는 부분을 상속해줄 클래스

    @CreatedDate //게시물 생성 시 동작(엔티티의 생성 시간 처리)
    @Column(name = "regdate", updatable = false) //db테이블에 필드명 지정, 업데이트 안됨
    private LocalDateTime regDate; //게시물 등록시간

    @LastModifiedDate //게시물 수정시 동작(최종 수정 시간을 자동으로 처리)
    @Column(name = "moddate") //업데이트 되어야함
    private LocalDateTime modDate; //게시물 수정시간

}

