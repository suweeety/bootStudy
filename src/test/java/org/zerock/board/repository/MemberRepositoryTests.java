package org.zerock.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.entity.Member;

import java.util.stream.IntStream;

@SpringBootTest //스프링 부트 테스트용 명시
public class MemberRepositoryTests {
    @Autowired //인터페이스 자동 주입
    private MemberRepository memberRepository;
    @Autowired //인터페이스 자동 주입
    private ReviewRepository reviewRepository;

    @Test
    public void insertMembers() { //테이블에 더미데이터 100개 추가
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@zerock.org")
                    .password("1111")
                    .name("USER" + i)
                    .build(); //빌더 패턴은 엔티티 @Builder 필수
            memberRepository.save(member);
            //jpa의 save 메서드를 실행하면 출력까지 진행
        });
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteMember() {
        Long mid = 1L; //Member의 mid
        Member member = Member.builder()
                .mid(mid)
                .build();
        //순서 주의!!
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);

    }

}
