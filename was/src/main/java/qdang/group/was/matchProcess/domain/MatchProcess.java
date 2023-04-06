package qdang.group.was.matchProcess.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class MatchProcess {

    @Id
    private Long id;
}
