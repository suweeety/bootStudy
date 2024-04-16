package org.zerock.board.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.board.entity.Member;
import org.zerock.board.entity.Movie;
import org.zerock.board.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    //EntityGraph 는 attributePaths 속성과 type 속성을 갖고있다.
    //FETCH 속성값은 attributePaths에 명시한 속성은 EAGER 처리, 나머진 LAZY 처리
    //LOAD 속성값은 attributePaths에 명시한 속성은 EAGER 처리, 나머진 엔티티 클래스에 명시되거나 기본 방식으로 처리
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> finfByMovie(Movie movie);

    @Modifying
    @Query("delete  from Review mr where mr.member = :member")
    void deleteByMember(Member member);
}
