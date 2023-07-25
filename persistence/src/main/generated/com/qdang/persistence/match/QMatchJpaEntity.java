package com.qdang.persistence.match;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMatchJpaEntity is a Querydsl query type for MatchJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatchJpaEntity extends EntityPathBase<MatchJpaEntity> {

    private static final long serialVersionUID = -1320527387L;

    public static final QMatchJpaEntity matchJpaEntity = new QMatchJpaEntity("matchJpaEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final TimePath<java.time.LocalTime> duration = createTime("duration", java.time.LocalTime.class);

    public final DateTimePath<java.time.LocalDateTime> endAt = createDateTime("endAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final BooleanPath isValid = createBoolean("isValid");

    public final EnumPath<MatchTypeJpa> matchTypeCode = createEnum("matchTypeCode", MatchTypeJpa.class);

    public final EnumPath<MatchTypeJpa> matchTypeName = createEnum("matchTypeName", MatchTypeJpa.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> userCount = createNumber("userCount", Integer.class);

    public QMatchJpaEntity(String variable) {
        super(MatchJpaEntity.class, forVariable(variable));
    }

    public QMatchJpaEntity(Path<? extends MatchJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMatchJpaEntity(PathMetadata metadata) {
        super(MatchJpaEntity.class, metadata);
    }

}

