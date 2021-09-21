package com.example.clip.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "disbursment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Disbursement {

	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "disbursment_sequence", sequenceName = "disbursment_sequence")
	@GeneratedValue(generator = "disbursment_sequence")
	private Long id;

	@Column(name = "amount")
	@NotNull
	private BigDecimal amount;

	@Column(name = "users_id")
	private Long userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_transaction")
	private Date lastTransaction;

	@OneToMany( cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnoreProperties("disbursement")
    private List<Payment> payment;
	

}
