package com.works.leagueservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
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
@JsonIgnoreProperties( allowGetters = true)
public abstract class BaseEntity implements Serializable {

    protected String cuser;
    protected String uuser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    protected Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    protected Date updatedAt;


    protected  int isActv;

    public String getCuser() {
        return this.cuser;
    }

    public String getUuser() {
        return this.uuser;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public int getIsActv() {
        return this.isActv;
    }

    public void setCuser(String cuser) {
        this.cuser = cuser;
    }

    public void setUuser(String uuser) {
        this.uuser = uuser;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setIsActv(int isActv) {
        this.isActv = isActv;
    }
}
