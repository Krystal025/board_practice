package com.practice.board.dao;

import com.practice.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BoardMapper {
    // 게시글 등록
    void save(BoardDto boardDto);

    // 게시글 목록 조회
    List<BoardDto> findAll();

    // 게시글 상세페이지 조회
    BoardDto findById(Long id);

    // 게시글 수정
    void update(BoardDto boardDto);

    // 게시글 삭제
    void delete(Long id);

    // 조회수 증가
    void updateHits(Long id);
}
