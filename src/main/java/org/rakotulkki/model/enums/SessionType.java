package org.rakotulkki.model.enums;

/**
 * @author jkuittin
 */
public enum SessionType {

	EXPLORATORY("Tutustumiskäynti"), THERAPY("Terapiakäynti");

	private final String value;

	SessionType(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
