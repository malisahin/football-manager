package com.works.leagueservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
@Getter
@Setter
@Embeddable
public class BaseEntity implements Serializable {

    @Column(name = "create_user", nullable = false, updatable = false)
    @CreatedBy
    protected String createUser;

    @Column(name = "update_user")
    @LastModifiedBy
    protected String updateUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    protected Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @LastModifiedDate
    protected Date updatedAt;

    @Column(name = "is_actv", nullable = false)
    protected int isActv = 1;

    @PrePersist
    public void setPersistData() {
        // TODO should be call from user tables.
        this.setCreateUser("c_mali");
        this.setCreatedAt(new Date());
    }

    @PreUpdate
    public void setUpdateData() {
        // TODO should be call from user tables.
        this.setUpdateUser("u_mali");
        this.setUpdatedAt(new Date());
    }

}
