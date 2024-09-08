package com.practice.board.repository;

import com.practice.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sql;  // MyBatis에서 제공하는 SQL 세션관리 클래스로 mapper 호출 (Mapper를 직접 주입받는 대신)

    public void save(BoardDto boardDto) {
        sql.insert("Board.save", boardDto);
        // "Board"는 Mapper의 namespace를 말함, "boardDto"는 DB로 넘길 객체
    }

    public List<BoardDto> findAll() {
        return sql.selectList("Board.findAll");
    }

    public void updateHits(Long id) {
        sql.update("Board.updateHits",  id);
    }

    public BoardDto findById(Long id){
        return sql.selectOne("Board.findById", id);
    }

    public void update(BoardDto boardDto) {
        sql.update("Board.update", boardDto);
    }

    public void delete(Long id) {
        sql.delete("Board.delete", id);
    }
}
