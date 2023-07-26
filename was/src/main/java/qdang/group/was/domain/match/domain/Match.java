package qdang.group.was.domain.match.domain;

import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import qdang.group.was.global.util.AuditingTimeEntity;

@Getter
@Entity
@Table(name = "q_match")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Match extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int matchTypeCode;

    @Enumerated(EnumType.STRING)
    private MatchType matchTypeName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean isDeleted;

    private boolean isValid;

    private LocalDateTime endAt;

    private LocalTime duration;

    private int userCount;
}