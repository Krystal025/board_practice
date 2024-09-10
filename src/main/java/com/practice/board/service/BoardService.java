package com.practice.board.service;

import com.practice.board.dao.BoardMapper;
import com.practice.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    // 게시글 등록
    public void save(BoardDto boardDto) {
        System.out.println("Saving BoardDto: " + boardDto);
        boardMapper.save(boardDto);
    }
    // 게시글 조회
    public List<BoardDto> findAll() {
        return boardMapper.findAll();
    }
    // 게시글 상세페이지 조회
    public BoardDto findById(Long id) {
        return boardMapper.findById(id);
    }
    // 게시글 수정
    public void update(BoardDto boardDto) {
        boardMapper.update(boardDto);
    }
    // 게시글 삭제
    public void delete(Long id) {
        boardMapper.delete(id);
    }
    // 조회수 증가
    public void updateHits(Long id) {
        boardMapper.updateHits(id);
    }
}
