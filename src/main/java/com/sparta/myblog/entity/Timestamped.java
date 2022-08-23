package com.sparta.myblog.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 상속했을 때, 자동으로 컬럼으로 인식합니다.
@EntityListeners(AuditingEntityListener.class) // 생성/수정 시간을 자동으로 반영하도록 설정
@Getter // Getter가 없으면 작동이 안 됨
public abstract class Timestamped { // abstract는 상속이 되어야지만 사용할 수 있는 클래스라는 것을 의미

    @CreatedDate // 생성일자임을 나타냅니다.
    private LocalDateTime createdAt; // LocalDateTime은 시간을 나타내는 자료형

    @LastModifiedDate // 마지막 수정일자임을 나타냅니다.
    private LocalDateTime modifiedAt;
}