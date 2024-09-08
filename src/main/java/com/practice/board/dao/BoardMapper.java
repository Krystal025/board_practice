package com.practice.board.dao;

import com.practice.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper
public interface BoardMapper {
    // 게시글 등록
    void save(BoardDto boardDto);

    // 게시글 조회
    List<BoardDto> findAll();

    // 게시글 상세페이지 조회 (id로 불러옴)
    BoardDto findById(Long id);

    // 조회수 업데이트
    void updateHits(Long id);

    // 게시글 수정
    void update(BoardDto boardDto);

    // 게시글 삭제
    void delete(Long id);
}
