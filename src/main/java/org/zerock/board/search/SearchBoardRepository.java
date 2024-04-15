package org.zerock.board.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.entity.Board;

public interface SearchBoardRepository { //검색을 위한 Repository확장
    Board search1(); //Board 타입 객체를 반환하는 메서드

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}
