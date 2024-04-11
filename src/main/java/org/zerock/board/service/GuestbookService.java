package org.zerock.board.service;

import org.zerock.board.dto.GuestbookDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Guestbook;

public interface GuestbookService {
    Long register(GuestbookDTO dto);

    //PageRequestDTO 파라미터로 받고 PageResultDTO 를 반환~! 하는 getList() 작성
    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    //default 기능을 활용해 구현클래스에서 동작할 수 있는 dtoToEntity()를 구성
    default Guestbook dtoToEntity(GuestbookDTO dto) {
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default GuestbookDTO entityToDTO(Guestbook entity) {
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
