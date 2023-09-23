package com.qdang.persistence.usermatchprocess;

import com.qdang.persistence.matchprocess.MatchProcessJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@Table(name = "q_user_match_process")
@DynamicInsert
@DynamicUpdate
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMatchProcessJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_process_id", nullable = false)
    private MatchProcessJpaEntity matchProcess;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Integer finishCushionScore;

    @Column(nullable = false)
    private Integer finishBankShotScore;

    @Column(nullable = false)
    private Integer ranking;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserMatchStatusJpa status;

    @Column(nullable = false)
    private Integer maxHighRun;

    @Column(nullable = false)
    private Integer highRun;

    @Column(nullable = false)
    private Integer deltaScore;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private TurnTypeJpa turnType;

    @Column(nullable = false)
    private Boolean isMyTurn;

    @Column(nullable = false)
    private Integer inningCount;

    @Column(nullable = false)
    private Integer succeedInningCount;

    @Column(nullable = false)
    private Integer failedInningCount;
}
