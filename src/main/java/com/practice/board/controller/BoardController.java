package com.practice.board.controller;

import com.practice.board.dto.BoardDto;
import com.practice.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
@RequiredArgsConstructor  //final이 적용된 필드를 매개변수로 이용하여 생성자를 만듦
public class BoardController {
    //생성자 주입 방식 (의존성 주입시 생성자를 통해 객체를 전달받는 방법)
    private final BoardService boardService;

    // 글 작성페이지 조회
    @GetMapping("/save")
    public String save(){
        return "save";
    }

    // 글 작성(등록)
    @PostMapping("/save")  //파라미터에 @ModelAttribute를 생략해도 사용자 정의 객체는 Spring MVC가 자동으로 해당 객체에 바인딩해줌)
    public String save(BoardDto boardDto){
        System.out.println("boardDto = " + boardDto);
        boardService.save(boardDto);
        // redirect : 화면을 띄우는게 아니라 다시 "/list"를 호출하는 것
        return "redirect:/list";
    }

    /* Spring MVC 패턴에서는 View 이름을 반환함 (이는 JSP나 Thymleaf와 같은 템플릿에 해당함)
     * 서버 사이드 렌더링과 연관되어 있어 서버가 데이터를 가져오고 html 뷰를 준비하는 역할을 함
     * 이때 Controller에서 View로 데이터를 전달할 때 Model 객체를 사용함
     */
    // 글 목록 조회
    @GetMapping("/list")
    public String getList(Model model){
        List<BoardDto> boardDtoList = boardService.findAll();
        model.addAttribute("boardList", boardDtoList);
        System.out.println("boardDtoList = " + boardDtoList);
        return "list";
    }

    // 글 상세페이지 조회
    @GetMapping("/{id}")
    public String getDetail(@PathVariable("id") Long id, Model model){
        // 조회수 처리
        boardService.updateHits(id);
        // 상세내용 가져오기
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("board", boardDto);
        System.out.println("boardDto = " + boardDto);
        return "detail";
    }

    // 수정할 글 조회
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("board", boardDto);
        return "update";
    }

    // 글 수정 요청
    @PostMapping("/update/{id}")
    public String update(BoardDto boardDto, Model model){
        // Dto 수정 요청
        boardService.update(boardDto);
        // 다시 id로 수정된 Dto를 가져옴
        BoardDto updatedDto = boardService.findById(boardDto.getId());
        model.addAttribute("board", updatedDto);
        return "detail";
    }

    // 글 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.delete(id);
        return "redirect:/list";
    }


}



















