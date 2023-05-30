package qdang.group.was.userMatchProcess.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "q_user_match_process")
public class UserMatchProcess {

    @Id
    @Column(name = "q_user_match_proccess_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
