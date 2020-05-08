package org.irdigital.cc.customers.domain.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@MappedSuperclass
public class BaseEntity extends AbstractAggregateRoot<BaseEntity> {

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Boolean active;
}
