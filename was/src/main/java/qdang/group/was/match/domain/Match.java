package qdang.group.was.match.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import qdang.group.was.matchProcess.domain.MatchProcess;
import qdang.group.was.userMatch.domain.UserMatch;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity(name = "q_match")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Match extends AuditingEntityListener {

    @Id
    @Column(name = "q_match_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "match")
    private List<UserMatch> userMatchList;

    @Column(name = "user_count")
    private int userCount;

    private int matchTypeCode;

    @Enumerated(EnumType.STRING)
    private MatchType matchTypeName;

    private LocalDateTime endAt;

    private Duration duration;

    private boolean deletedTf;

    private boolean validTf;

    @OneToMany(mappedBy = "match")
    private List<UserMatch> userMatcheList;

    @OneToMany(mappedBy = "match")
    private List<MatchProcess> matchProcessList;
}