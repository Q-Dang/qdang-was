package com.qdang.core.usermatch;

import com.qdang.core.match.MatchJpaEntity;
import com.qdang.core.user.UserJpaEntity;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@Table(name = "q_user_match")
@DynamicInsert
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMatchJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private MatchJpaEntity match;

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
