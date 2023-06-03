package qdang.group.was.userMatchProcess.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class UserMatchProcess {

    @Id
    private Long id;

}
