package org.rakotulkki.model.hibernate;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author jkuittin
 */
@Entity
@Table(name = "sessions")
public class Session {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int")
	private Long id;

	@Column
	private DateTime start;

	@Column
	private DateTime end;

	@Column
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "customer_id", columnDefinition = "int")
	private Customer customer;

}
