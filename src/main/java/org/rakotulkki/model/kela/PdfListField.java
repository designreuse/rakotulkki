package org.rakotulkki.model.kela;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jkuittin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface PdfListField {

	String firstIndex();

	String rowLength();

	String lastIndex();

}
