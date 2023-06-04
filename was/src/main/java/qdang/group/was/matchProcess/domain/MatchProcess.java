package qdang.group.was.matchProcess.domain;

import jakarta.persistence.*;
import lombok.Getter;
import qdang.group.was.match.domain.Match;
import qdang.group.was.user.domain.User;

@Getter
@Entity(name = "q_match_process")
public class MatchProcess {

    @Id
    @Column(name = "q_match_process_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q_match_id")
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q_user_id")
    private User user;

    private int processCount;

    private int turnCount;

    private int phaseCount;

    private boolean validTf;
}
