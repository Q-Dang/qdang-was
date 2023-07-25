package com.qdang.persistence.phoneauth;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPhoneAuthJpaEntity is a Querydsl query type for PhoneAuthJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhoneAuthJpaEntity extends EntityPathBase<PhoneAuthJpaEntity> {

    private static final long serialVersionUID = 1386005795L;

    public static final QPhoneAuthJpaEntity phoneAuthJpaEntity = new QPhoneAuthJpaEntity("phoneAuthJpaEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> expiredAt = createDateTime("expiredAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final BooleanPath isPhoneAuth = createBoolean("isPhoneAuth");

    public final StringPath phone = createString("phone");

    public final StringPath phoneCode = createString("phoneCode");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QPhoneAuthJpaEntity(String variable) {
        super(PhoneAuthJpaEntity.class, forVariable(variable));
    }

    public QPhoneAuthJpaEntity(Path<? extends PhoneAuthJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPhoneAuthJpaEntity(PathMetadata metadata) {
        super(PhoneAuthJpaEntity.class, metadata);
    }

}

