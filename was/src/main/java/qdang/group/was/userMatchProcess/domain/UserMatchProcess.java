package qdang.group.was.userMatchProcess.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Getter
@Entity(name = "q_user_match_process")
public class UserMatchProcess {

    @Id
    @Column(name = "q_user_match_proccess_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
