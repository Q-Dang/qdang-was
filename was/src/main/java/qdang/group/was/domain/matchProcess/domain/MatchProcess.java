package qdang.group.was.domain.matchProcess.domain;

import java.time.Duration;
import java.time.LocalTime;
import javax.persistence.Column;
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
import qdang.group.was.domain.match.domain.Match;
import qdang.group.was.domain.user.domain.User;

@Getter
@Entity
@Table(name = "q_match_process")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "play_user_id")
    private User user;

    private LocalTime duration;

    private int processCount;

    private int turnCount;

    private int phaseCount;

    private boolean isValid;
}

