package com.qdang.persistence.matchprocess;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMatchProcessJpaEntity is a Querydsl query type for MatchProcessJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatchProcessJpaEntity extends EntityPathBase<MatchProcessJpaEntity> {

    private static final long serialVersionUID = 583797171L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMatchProcessJpaEntity matchProcessJpaEntity = new QMatchProcessJpaEntity("matchProcessJpaEntity");

    public final TimePath<java.time.LocalTime> duration = createTime("duration", java.time.LocalTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isValid = createBoolean("isValid");

    public final com.qdang.persistence.match.QMatchJpaEntity match;

    public final NumberPath<Integer> phaseCount = createNumber("phaseCount", Integer.class);

    public final com.qdang.persistence.user.QUserJpaEntity player;

    public final NumberPath<Integer> processCount = createNumber("processCount", Integer.class);

    public final NumberPath<Integer> turnCount = createNumber("turnCount", Integer.class);

    public QMatchProcessJpaEntity(String variable) {
        this(MatchProcessJpaEntity.class, forVariable(variable), INITS);
    }

    public QMatchProcessJpaEntity(Path<? extends MatchProcessJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMatchProcessJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMatchProcessJpaEntity(PathMetadata metadata, PathInits inits) {
        this(MatchProcessJpaEntity.class, metadata, inits);
    }

    public QMatchProcessJpaEntity(Class<? extends MatchProcessJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.match = inits.isInitialized("match") ? new com.qdang.persistence.match.QMatchJpaEntity(forProperty("match")) : null;
        this.player = inits.isInitialized("player") ? new com.qdang.persistence.user.QUserJpaEntity(forProperty("player"), inits.get("player")) : null;
    }

}

