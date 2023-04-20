package qdang.group.was.match.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MatchType {

    pool(0, "pool"),
    BALL4(1, "ball4"),
    BALL3(2, "ball3");

    private final int matchTypeCode;
    private final String matchTypeName;

}