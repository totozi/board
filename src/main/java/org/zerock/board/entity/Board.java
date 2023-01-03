package org.zerock.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer") // writer은 항상 toString 결과에서 제외
public class Board extends BaseEntity {
    // Member의 이메일을 FK로 참조

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    @ManyToOne
    private Member writer; // 연관관계 지정

}
