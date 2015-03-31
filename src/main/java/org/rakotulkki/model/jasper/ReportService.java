package org.rakotulkki.model.jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.rakotulkki.model.dto.InvoiceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author jkuittin
 */
public class ReportService {

	private final static Logger log = LoggerFactory.getLogger(ReportService.class);

	private final String REPORT_PATH = "/jasper";

	public static final String INVOICE = "invoice-template.jasper";

	public void printInvoice(final InvoiceDTO invoice, final OutputStream outputStream) throws FileNotFoundException {
		InputStream reportStream = getClass().getResourceAsStream(REPORT_PATH + "/" + INVOICE);

		List<InvoiceDTO> data = new ArrayList<>();
		data.add(invoice);

		JRDataSource datasource = new JRBeanCollectionDataSource(data, true);

		try {
			JasperPrint print = JasperFillManager.fillReport(reportStream, new HashMap<String, Object>(), datasource);
			JasperExportManager.exportReportToPdfStream(print, outputStream);
		} catch (JRException e) {
			throw new RuntimeException("Could not generate report", e);
		}
	}

}
