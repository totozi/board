package org.zerock.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board") // board은 항상 toString 결과에서 제외
public class Reply extends BaseEntity {
    // 회원이 아닌 사람도 댓글 가능 -> Board와 연관관계 맺지 않도록 처리

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String text;

    private String replyer;

    @ManyToOne
    private Board board; // 연관관계 지정

}
