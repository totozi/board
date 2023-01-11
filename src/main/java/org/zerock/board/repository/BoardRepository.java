package org.zerock.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.repository.search.SearchBoardRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {

    // 1개의 로우(Object) 내에 Obejct[]로 나옴
    @Query("select b, w from Board b left join b.writer w where b.bno = :bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    // 글이랑 글에 속한 댓글 같이 출력하기
    @Query("SELECT b, r FROM Board b LEFT JOIN Reply r ON r.board = b WHERE b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);
    
    @Query(value = "SELECT b, w, count(r) " +
                   " FROM Board b " + 
                   " LEFT JOIN b.writer w " +
                   " LEFT JOIN Reply r ON r.board = b " +
                   " GROUP BY b",
            countQuery = "SELECT count(b) FROM Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable); // 목록 화면에 필요한 데이터

    @Query(value = "SELECT b, w, count(r) " +
            " FROM Board b " +
            " LEFT JOIN b.writer w " +
            " LEFT JOIN Reply r ON r.board = b " +
            " WHERE b.bno = :bno" +
            " GROUP BY b",
            countQuery = "SELECT count(b) FROM Board b")
    Object getBoardByBno(@Param("bno") Long bno);
}
