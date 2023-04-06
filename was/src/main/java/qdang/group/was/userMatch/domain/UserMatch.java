package qdang.group.was.userMatch.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class UserMatch {

    @Id
    private Long id;

}
