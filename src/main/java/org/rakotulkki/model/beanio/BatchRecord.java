package org.rakotulkki.model.beanio;

import org.beanio.annotation.Field;
import org.beanio.builder.Align;

import java.util.Date;

/**
 * @author jkuittin
 */
public class BatchRecord {

	private static final String id = "0";

	private static final int ZERO = '0';

	@Field(at = 0, length = 1, literal = id, required = true)
	private Integer recordId;

	@Field(at = 21, length = 6, format = "yyMMdd", required = true)
	private Date paymentDate;

	@Field(at = 21, length = 4, format = "hhmm", required = true)
	private Date paymentTime;

	@Field(at = 27, length = 2, align = Align.LEFT, literal = "", keepPadding = true,
		   required = true)
	private String bankId;

	@Field(at = 27, length = 2, align = Align.LEFT, literal = "", keepPadding = true,
		   required = true)
	private String referenceId;

	@Field(at = 27, length = 2, align = Align.LEFT, literal = "", keepPadding = true,
		   required = true)
	private String currency;

	@Field(at = 27, length = 2, align = Align.LEFT, literal = "", keepPadding = true,
		   required = true)
	private String bankReservation;

	@Field(at = 51, length = 38, literal = "", align = Align.LEFT, keepPadding = true, required = false)
	private String reservedField;

}
