package qdang.group.was.userMatch.domain;

import jakarta.persistence.*;
import lombok.Getter;
import qdang.group.was.global.util.AuditingTimeEntity;
import qdang.group.was.match.domain.Match;
import qdang.group.was.user.domain.User;

@Getter
@Entity(name = "q_user_match")
public class UserMatch extends AuditingTimeEntity {

    @Id
    @Column(name = "q_user_match_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q_user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "q_match_id")
    private Match match;

    private int targetScore;
    private int finishCushionTargetScore;
    private int finishBankShotTargetScore;
    private boolean validTf;
    private int score;
    private int finishCushionScore;
    private int finishBankShotScore;
    private int ranking;
    private int maxHighRun;
    private int average;
    private int inningCount;
    private int succeedInningCount;
    private int failedInningCount;
}
