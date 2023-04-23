package com.neo.neouserservice.common.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @EmbeddedId
    private ID id;

    @CreatedBy
    @AttributeOverride(name = "id", column = @Column(name = "created_by_id", updatable = false))
    private ID creator;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedBy
    @AttributeOverride(name = "id", column = @Column(name = "modified_by_id", updatable = false))
    private ID modifier;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private boolean deleted = false;

    private boolean active = true;

    @PrePersist
    public void generateIdAndDate() {
        if(this.getId() == null) {
            this.setId(ID.generateId());
            this.setCreatedAt(LocalDateTime.now());
        }
    }

}
