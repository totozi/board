package org.zerock.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoard() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Member member = Member.builder().email("user" + i + "@aaa.com").build();

            Board board = Board.builder()
                    .title("Title...." +i)
                    .content("Content...." + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);

        });
    }

    @Transactional // board 로딩 이후 Member를 로딩하기 위해 데이터베이스 연결을 유지하기 하도록 하기 위함.
    // 밑의 메서드를 하나의 트랜잭션으로 인식하게 함
    @Test
    public void testRead1() {

        Optional<Board> result = boardRepository.findById(100L); // 데이터베이스에 존재하는 번호로 글 읽어옴

        Board board = result.get(); // Board 객체 생성

        System.out.println(board);              // Board
        System.out.println(board.getWriter());  // Member who uploaded the post


    }
}
