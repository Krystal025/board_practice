package com.practice.board.controller;

import com.practice.board.dto.BoardDto;
import com.practice.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor  //final이 적용된 필드를 매개변수로 이용하여 생성자를 만듦
public class BoardController {

    //생성자 주입 방식 (의존성 주입시 생성자를 통해 객체를 전달받는 방법)
    private final BoardService boardService;

    // 게시글 등록 요청
    @Transactional
    @PostMapping("/save")  //파라미터에 @ModelAttribute를 생략해도 사용자 정의 객체는 Spring MVC가 자동으로 해당 객체에 바인딩해줌)
    public ResponseEntity<?> save(@RequestBody BoardDto boardDto) {
        try{
            System.out.println("BoardDto: " + boardDto);
            boardService.save(boardDto);
            return new ResponseEntity<>("Save Complete!", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Save", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 게시글 목록 조회
    @GetMapping("/list")
    public List<BoardDto> getList() {
        return boardService.findAll();
    }

    // 게시글 상세페이지 조회
    @GetMapping("/{id}")
    public BoardDto getDetail(@PathVariable("id") Long id) {
        // 조회수 처리
        boardService.updateHits(id);
        // 상세내용 가져오기
        return boardService.findById(id);
    }

    // 게시글 수정 요청
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody BoardDto boardDto) {
        try{
            boardService.update(boardDto);
            return new ResponseEntity<>("Update Complete", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Update", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 게시글 삭제 요청
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            boardService.delete(id);
            return new ResponseEntity<>("Delete Complete", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to Delete", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}



















