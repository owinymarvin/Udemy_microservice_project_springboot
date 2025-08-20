package com.dmarv.microservice.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
//Lombok to get the getters and setters, convert them to string
@Getter @Setter @ToString
public class BaseEntity {
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;
    @Column(name = "updated_by", insertable = false)
    private String updatedBy;
}
