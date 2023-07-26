package qdang.group.was.domain.match.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MatchType {

    THREE_BALL(2),
    FOUR_BALL(1),
    POCKET_BALL(0);

    private final int matchTypeCode;
}