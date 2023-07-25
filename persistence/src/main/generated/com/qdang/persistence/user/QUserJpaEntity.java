package com.qdang.persistence.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserJpaEntity is a Querydsl query type for UserJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserJpaEntity extends EntityPathBase<UserJpaEntity> {

    private static final long serialVersionUID = -159364365L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserJpaEntity userJpaEntity = new QUserJpaEntity("userJpaEntity");

    public final DateTimePath<java.time.LocalDateTime> accessAt = createDateTime("accessAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> accessCount = createNumber("accessCount", Integer.class);

    public final StringPath address = createString("address");

    public final DateTimePath<java.time.LocalDateTime> agreeUpdateAt = createDateTime("agreeUpdateAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> average = createNumber("average", Integer.class);

    public final NumberPath<Integer> battingAverage = createNumber("battingAverage", Integer.class);

    public final DatePath<java.time.LocalDate> birthday = createDate("birthday", java.time.LocalDate.class);

    public final StringPath detailAddress = createString("detailAddress");

    public final NumberPath<Integer> failedInningCount = createNumber("failedInningCount", Integer.class);

    public final StringPath fcmToken = createString("fcmToken");

    public final EnumPath<GenderJpa> gender = createEnum("gender", GenderJpa.class);

    public final NumberPath<Integer> highRun = createNumber("highRun", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isLeaving = createBoolean("isLeaving");

    public final BooleanPath isPhoneAuth = createBoolean("isPhoneAuth");

    public final BooleanPath isResting = createBoolean("isResting");

    public final BooleanPath joinAgree = createBoolean("joinAgree");

    public final DateTimePath<java.time.LocalDateTime> joinAt = createDateTime("joinAt", java.time.LocalDateTime.class);

    public final QUserJpaEntity joinStaff;

    public final StringPath loginId = createString("loginId");

    public final NumberPath<Integer> matchCount = createNumber("matchCount", Integer.class);

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final DateTimePath<java.time.LocalDateTime> phoneAuthAt = createDateTime("phoneAuthAt", java.time.LocalDateTime.class);

    public final StringPath phoneAuthCode = createString("phoneAuthCode");

    public final NumberPath<Integer> proficiency = createNumber("proficiency", Integer.class);

    public final StringPath profileImage = createString("profileImage");

    public final NumberPath<Integer> sluggingCount = createNumber("sluggingCount", Integer.class);

    public final NumberPath<Integer> sluggingPercentage = createNumber("sluggingPercentage", Integer.class);

    public final StringPath statusMessage = createString("statusMessage");

    public final NumberPath<Integer> succeedInningCount = createNumber("succeedInningCount", Integer.class);

    public final NumberPath<Integer> totalInningCount = createNumber("totalInningCount", Integer.class);

    public final ListPath<com.qdang.persistence.usermatch.UserMatchJpaEntity, com.qdang.persistence.usermatch.QUserMatchJpaEntity> userMatchList = this.<com.qdang.persistence.usermatch.UserMatchJpaEntity, com.qdang.persistence.usermatch.QUserMatchJpaEntity>createList("userMatchList", com.qdang.persistence.usermatch.UserMatchJpaEntity.class, com.qdang.persistence.usermatch.QUserMatchJpaEntity.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public final EnumPath<UserRoleJpa> userRole = createEnum("userRole", UserRoleJpa.class);

    public QUserJpaEntity(String variable) {
        this(UserJpaEntity.class, forVariable(variable), INITS);
    }

    public QUserJpaEntity(Path<? extends UserJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserJpaEntity(PathMetadata metadata, PathInits inits) {
        this(UserJpaEntity.class, metadata, inits);
    }

    public QUserJpaEntity(Class<? extends UserJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.joinStaff = inits.isInitialized("joinStaff") ? new QUserJpaEntity(forProperty("joinStaff"), inits.get("joinStaff")) : null;
    }

}

