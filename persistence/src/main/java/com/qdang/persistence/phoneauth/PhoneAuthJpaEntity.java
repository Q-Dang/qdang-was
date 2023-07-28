package com.qdang.persistence.phoneauth;


import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "phone_auth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneAuthJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    private String phoneCode;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime expiredAt;

    private Boolean isDeleted;

    private Boolean isPhoneAuth;
}
