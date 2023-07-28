package com.qdang.persistence.usermatchprocess;

import com.qdang.persistence.matchprocess.MatchProcessJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
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
@Table(name = "q_user_match_process")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMatchProcessJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;

    private Integer finishCushionScore;

    private Integer finishBankShotScore;

    private Integer ranking;

    private String status;

    private Integer maxHighRun;

    private Integer highRun;

    private Integer deltaScore;

    private String turnType;

    private Boolean isMyTurn;

    private Integer inningCount;

    private Integer succeedInningCount;

    private Integer failedInningCount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_process_id")
    private MatchProcessJpaEntity matchProcess;
}
