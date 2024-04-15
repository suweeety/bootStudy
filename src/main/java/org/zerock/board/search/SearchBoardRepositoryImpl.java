package org.zerock.board.search;

import com.fasterxml.jackson.databind.ObjectReader;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.QBoard;
import org.zerock.board.entity.QMember;
import org.zerock.board.entity.QReply;

import java.util.List;
import java.util.stream.Collectors;

//Querydsl을 사용하여 Board 엔티티에 대한 검색을 수행
//QuerydslRepositorySupport의 생성자를 호출하여 Board 엔티티를 기반으로 한다고 선언
@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository{

    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board search1() { //특정 게시물 번호에 해당하는 게시물을 검색하는 기능을 구현
        log.info("search1............");

        QBoard board = QBoard.board; //Querydsl을 사용하여 Board 엔티티에 대한 쿼리를 생성하기 위해 QBoard 클래스를 사용
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board); //Querydsl의 from 메서드를 사용하여 쿼리를 시작합니다.
        jpqlQuery.leftJoin(member).on(board.writer.eq(member)); //Board와 Member 엔티티를 조인
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board)); //Board와 Reply 엔티티를 조인

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count()); //쿼리 결과로 Tuple을 선택
        tuple.groupBy(board); //Board를 기준으로 그룹화

        log.info("------------------------------");
        log.info(tuple);
        log.info("------------------------------");

        List<Tuple> result = tuple.fetch(); //쿼리를 실행하고 결과를 가져옵니다.

        log.info(result);

        return null; //결과를 반환합니다. 여기서는 결과를 반환하지 않고 null을 반환합니다.
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        log.info("searchPage................");

        QBoard board = QBoard.board; //Board 엔티티에 대한 쿼리를 생성하기 위해 QBoard 클래스를 사용
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board); //Querydsl의 from 메서드를 사용하여 쿼리를 시작
        jpqlQuery.leftJoin(member).on(board.writer.eq(member)); //Board와 Member 엔티티를 조인
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board)); //Board와 Reply 엔티티를 조인

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, reply.count()); //쿼리 결과로 Tuple을 선택

        BooleanBuilder booleanBuilder = new BooleanBuilder(); //Boolean 조건 생성
        BooleanExpression expression = board.bno.gt(0L); //게시물 번호 0보다 큰 경우를 조건으로 설정

        booleanBuilder.and(expression); //생성한 조건을 추가

        if (type != null) { //검색 타입이 존재하는 경우
            String[] typeArr = type.split(""); //검색 타입을 배열로 분할
            //검색 조건을 작성하기
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t : typeArr) {
                switch (t) {
                    case "t": //제목 검색인 경우
                        conditionBuilder.or(board.title.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(member.email.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder); //작성된 조건을 전체 조건에 추가
        }
        tuple.where(booleanBuilder); //조건 적용

        //현재 페이지의 정렬 정보를 가져옴
        Sort sort = pageable.getSort();

        //tuple.orderBy(board.bno.desc());

        sort.stream().forEach(order -> { //정렬 정보를 순회하면서 Querydsl에 정렬 조건을 추가
            Order direction = order.isAscending()? Order.ASC: Order.DESC; //오름차순인지 내림차순인지 확인하여 Order 객체 생성
            String prop = order.getProperty(); //정렬할 속성 가져옴

            PathBuilder orderByExpression = new PathBuilder(Board.class, "board"); //정렬에 사용할 표현식을 생성

            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop))); //정렬 조건 추가
        });

        tuple.groupBy(board); //Board를 기준으로 그룹화

        //page 처리를 위해 offset과 limit을 설정
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch(); //쿼리를 실행하고 결과를 가져옴

        log.info(result);

        long count = tuple.fetchCount(); //전체 결과 수를 가져오기

        log.info("Count : " + count);

        return new PageImpl<Object[]>( //PageImpl 객체를 생성하여 페이지네이션된 결과를 반환
          result.stream().map(t -> t.toArray()).collect(Collectors.toList()), //결과를 Object 배열로 변환하고 리스트로 만듭니다.
                pageable, //현재 페이지 정보를 유지
                count); //전체 결과 수를 설정
        }


}
