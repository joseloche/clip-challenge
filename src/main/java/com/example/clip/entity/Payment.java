package com.example.clip.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Payment implements Serializable{

	private static final long serialVersionUID = 928236305671113190L;

   @Id
	@SequenceGenerator(initialValue = 3, allocationSize = 1, name = "payment_sequence", sequenceName = "payment_sequence")
	@GeneratedValue(generator = "payment_sequence")
   private long id;

    @Column(name = "amount")
    private BigDecimal amount;

	@Column(name = "estatus")
	private String status;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "last_transaction")
	private Date lTransaction;
	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private Users users;
	
	
}
