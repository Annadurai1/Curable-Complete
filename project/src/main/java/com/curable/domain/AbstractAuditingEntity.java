package com.curable.domain;

import java.time.Instant;
import java.time.ZoneOffset;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@Audited
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity {

	@CreatedBy
	@Column(name = "createdBy", nullable = false, length = 50, updatable = false)
	@JsonIgnore
	private String createdBy = "notgiven";

	@CreatedDate
	@Column(name = "createdDate", updatable = false)
	@JsonIgnore
	private Instant createdDate = Instant.now().atOffset(ZoneOffset.UTC).toInstant();

	@LastModifiedBy
	@Column(name = "lastModifiedBy", length = 50)
	@JsonIgnore
	private String lastModifiedBy;

	@LastModifiedDate
	@Column(name = "lastModifiedDate")
	@JsonIgnore
	private Instant lastModifiedDate = Instant.now().atOffset(ZoneOffset.UTC).toInstant();

	@Column(name = "isRecordDeleted", columnDefinition = "boolean default false", nullable = false)
	@JsonIgnore
	private Boolean isRecordDeleted = false;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Boolean getIsRecordDeleted() {
		return isRecordDeleted;
	}

	public void setIsRecordDeleted(Boolean isRecordDeleted) {
		this.isRecordDeleted = isRecordDeleted;
	}

	@PrePersist
	protected void prePersist() {
		if (this.createdDate == null)
			createdDate = Instant.now().atOffset(ZoneOffset.UTC).toInstant();
		if (this.lastModifiedDate == null)
			lastModifiedDate = Instant.now().atOffset(ZoneOffset.UTC).toInstant();
	}

	@PreUpdate
	protected void preUpdate() {
		this.lastModifiedDate = Instant.now().atOffset(ZoneOffset.UTC).toInstant();
	}

}
