package com.qdang.persistence.matchprocess;

import com.qdang.persistence.match.MatchJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "q_match_process")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchProcessJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private MatchJpaEntity match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private UserJpaEntity user;

    private LocalTime duration;

    private int processCount;

    private int turnCount;

    private int phaseCount;

    private boolean isValid;
}

