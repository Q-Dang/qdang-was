package com.qdang.persistence.usermatchprocess;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserMatchProcessJpaEntity is a Querydsl query type for UserMatchProcessJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserMatchProcessJpaEntity extends EntityPathBase<UserMatchProcessJpaEntity> {

    private static final long serialVersionUID = -686746317L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserMatchProcessJpaEntity userMatchProcessJpaEntity = new QUserMatchProcessJpaEntity("userMatchProcessJpaEntity");

    public final NumberPath<Integer> deltaScore = createNumber("deltaScore", Integer.class);

    public final NumberPath<Integer> failedInningCount = createNumber("failedInningCount", Integer.class);

    public final NumberPath<Integer> finishBankShotScore = createNumber("finishBankShotScore", Integer.class);

    public final NumberPath<Integer> finishCushionScore = createNumber("finishCushionScore", Integer.class);

    public final NumberPath<Integer> highRun = createNumber("highRun", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> inningCount = createNumber("inningCount", Integer.class);

    public final BooleanPath isMyTurn = createBoolean("isMyTurn");

    public final com.qdang.persistence.matchprocess.QMatchProcessJpaEntity matchProcess;

    public final NumberPath<Integer> maxHighRun = createNumber("maxHighRun", Integer.class);

    public final NumberPath<Integer> ranking = createNumber("ranking", Integer.class);

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final EnumPath<UserMatchStatusJpa> status = createEnum("status", UserMatchStatusJpa.class);

    public final NumberPath<Integer> succeedInningCount = createNumber("succeedInningCount", Integer.class);

    public final EnumPath<TurnTypeJpa> turnType = createEnum("turnType", TurnTypeJpa.class);

    public final com.qdang.persistence.user.QUserJpaEntity user;

    public QUserMatchProcessJpaEntity(String variable) {
        this(UserMatchProcessJpaEntity.class, forVariable(variable), INITS);
    }

    public QUserMatchProcessJpaEntity(Path<? extends UserMatchProcessJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserMatchProcessJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserMatchProcessJpaEntity(PathMetadata metadata, PathInits inits) {
        this(UserMatchProcessJpaEntity.class, metadata, inits);
    }

    public QUserMatchProcessJpaEntity(Class<? extends UserMatchProcessJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.matchProcess = inits.isInitialized("matchProcess") ? new com.qdang.persistence.matchprocess.QMatchProcessJpaEntity(forProperty("matchProcess"), inits.get("matchProcess")) : null;
        this.user = inits.isInitialized("user") ? new com.qdang.persistence.user.QUserJpaEntity(forProperty("user"), inits.get("user")) : null;
    }

}

