package qdang.group.was.match.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Match {

    @Id
    private Long id;

}
