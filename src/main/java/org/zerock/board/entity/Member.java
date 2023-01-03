package org.zerock.board.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity {

    // 이메일 주소 PK
    @Id
    private String email;

    private String password;

    private String name;


}
