package com.qdang.persistence.usermatch;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserMatchJpaEntity is a Querydsl query type for UserMatchJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserMatchJpaEntity extends EntityPathBase<UserMatchJpaEntity> {

    private static final long serialVersionUID = -1654301221L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserMatchJpaEntity userMatchJpaEntity = new QUserMatchJpaEntity("userMatchJpaEntity");

    public final NumberPath<Integer> average = createNumber("average", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> failedInningCount = createNumber("failedInningCount", Integer.class);

    public final NumberPath<Integer> finishBankShotScore = createNumber("finishBankShotScore", Integer.class);

    public final NumberPath<Integer> finishBankShotTargetScore = createNumber("finishBankShotTargetScore", Integer.class);

    public final NumberPath<Integer> finishCushionScore = createNumber("finishCushionScore", Integer.class);

    public final NumberPath<Integer> finishCushionTargetScore = createNumber("finishCushionTargetScore", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> inningCount = createNumber("inningCount", Integer.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final com.qdang.persistence.match.QMatchJpaEntity match;

    public final NumberPath<Integer> maxHighRun = createNumber("maxHighRun", Integer.class);

    public final NumberPath<Integer> ranking = createNumber("ranking", Integer.class);

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final NumberPath<Integer> sluggingCount = createNumber("sluggingCount", Integer.class);

    public final NumberPath<Integer> succeedInningCount = createNumber("succeedInningCount", Integer.class);

    public final NumberPath<Integer> targetScore = createNumber("targetScore", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.qdang.persistence.user.QUserJpaEntity user;

    public QUserMatchJpaEntity(String variable) {
        this(UserMatchJpaEntity.class, forVariable(variable), INITS);
    }

    public QUserMatchJpaEntity(Path<? extends UserMatchJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserMatchJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserMatchJpaEntity(PathMetadata metadata, PathInits inits) {
        this(UserMatchJpaEntity.class, metadata, inits);
    }

    public QUserMatchJpaEntity(Class<? extends UserMatchJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.match = inits.isInitialized("match") ? new com.qdang.persistence.match.QMatchJpaEntity(forProperty("match")) : null;
        this.user = inits.isInitialized("user") ? new com.qdang.persistence.user.QUserJpaEntity(forProperty("user"), inits.get("user")) : null;
    }

}

