package com.qdang.core.usermatch;

import com.qdang.core.match.Match;
import com.qdang.core.user.User;
import java.time.LocalDateTime;
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
@Table(name = "q_user_match")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    private Integer targetScore;
    private Integer finishCushionTargetScore;
    private Integer finishBankShotTargetScore;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isDeleted;
    private Integer score;
    private Integer finishCushionScore;
    private Integer finishBankShotScore;
    private Integer ranking;
    private Integer maxHighRun;
    private Integer average;
    private Integer inningCount;
    private Integer succeedInningCount;
    private Integer failedInningCount;
}
