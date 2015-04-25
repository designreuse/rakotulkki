package org.rakotulkki.services;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.commons.lang.WordUtils;
import org.rakotulkki.model.kela.PdfField;
import org.rakotulkki.model.kela.PdfListField;
import org.rakotulkki.model.kela.PdfReport;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

/**
 * @author jkuittin
 */
public class KelaReportService {

	private static final String reportPath = "/pdf/kela/";
	private static final String fieldAbbreviation = "tx";

	public void generateReport(final PdfReport report, final OutputStream os) throws IOException, DocumentException {
		PdfReader reader = loadReport(report);

		PdfStamper stamper = new PdfStamper(reader, os);
		AcroFields form = stamper.getAcroFields();
		form.setGenerateAppearances(true);

		generateReport(stamper, report);

		stamper.close();
		os.flush();
	}

	private PdfReader loadReport(final PdfReport report) throws IOException {
		URL url = KelaReportService.class.getResource(reportPath + report.getPdfTemplate());
		PdfReader reader = new PdfReader(url);
		PdfReader.unethicalreading = true;
		return reader;
	}

	private void generateReport(final PdfStamper pdf, final PdfReport report) throws IOException, DocumentException {
		AcroFields pdfFields = pdf.getAcroFields();

		for (Field f : report.getClass().getDeclaredFields()) {
			if (f.isAnnotationPresent(PdfField.class)) {
				parseField(report, report, pdfFields, f, null);
			} else if (f.isAnnotationPresent(PdfListField.class)) {
				PdfListField meta = f.getAnnotation(PdfListField.class);
				Integer idx = Integer.valueOf(meta.firstIndex());
				Integer lastIndex = Integer.valueOf(meta.lastIndex());
				Integer rowLength = Integer.valueOf(meta.rowLength());

				for (Object o : getListValueFromObject(report, f.getName())) {
					for (Field f2 : o.getClass().getDeclaredFields()) {
						parseField(report, o, pdfFields, f2, idx);
						// TODO: check lastIndex
					}
					idx += rowLength;
				}
			}
		}
	}

	private void parseField(final PdfReport report, Object o, final AcroFields pdfFields, final Field f, Integer index)
		throws IOException, DocumentException {
		f.setAccessible(true);
		PdfField meta = f.getAnnotation(PdfField.class);
		for (String s : meta.fieldIndexes()) {
			Integer v = Integer.valueOf(s);
			if (index != null) {
				index = v + index;
			} else {
				index = v;
			}
			String value = getValueFromObject(o, f.getName());
			String key = String.format("%s%02d", fieldAbbreviation, index);
			pdfFields.setField(key, value);
		}
	}

	/**
	 * Gets value from object using reflection.
	 *
	 * @param object    the object
	 * @param fieldName the field
	 * @return the value
	 */
	private String getValueFromObject(Object object, String fieldName) {

		// get class
		Class clazz = object != null ? object.getClass() : null;
		if (clazz == null) {
			return null;
		}

		// get object value using reflection
		String getterName = "get" + WordUtils.capitalize(fieldName);
		;
		try {
			@SuppressWarnings("unchecked") Method method = clazz.getMethod(getterName);
			Object valueObject = method.invoke(object, (Object[]) null);
			return valueObject != null ? valueObject.toString() : "";
		} catch (Exception e) {
			// ignore all reflection errors
		}

		return null;
	}

	/**
	 * Gets list value from object using reflection.
	 *
	 * @param object    the object
	 * @param fieldName the field
	 * @return the value
	 */
	private List<Object> getListValueFromObject(Object object, String fieldName) {

		// get class
		Class clazz = object != null ? object.getClass() : null;
		if (clazz == null) {
			return null;
		}

		// get object value using reflection
		String getterName = "get" + WordUtils.capitalize(fieldName);
		;
		try {
			@SuppressWarnings("unchecked") Method method = clazz.getMethod(getterName);
			Object valueObject = method.invoke(object, (Object[]) null);
			@SuppressWarnings("unchecked") List<Object> result = (List<Object>) valueObject;
			return result;
		} catch (Exception e) {
			// ignore all reflection errors
		}

		return null;
	}

}
