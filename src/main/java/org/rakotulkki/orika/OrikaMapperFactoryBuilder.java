package org.rakotulkki.orika;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.rakotulkki.model.dto.*;
import org.rakotulkki.model.hibernate.*;

import java.util.Date;

/**
 * @author jkuittin
 */
public class OrikaMapperFactoryBuilder {

	public DefaultMapperFactory build() {
		DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

		registerClassMaps(mapperFactory);
		registerConverters(mapperFactory);

		return mapperFactory;
	}

	private void registerConverters(final DefaultMapperFactory factory) {
		factory.getConverterFactory().registerConverter(
			new PassThroughConverter(DateTime.class, DateMidnight.class, LocalDateTime.class, LocalDate.class,
				LocalTime.class));

		/**
		 * Converter for handling Date to LocalTime conversion
		 */
		factory.getConverterFactory().registerConverter(new BidirectionalConverter<Date, LocalTime>() {
			@Override
			public LocalTime convertTo(final Date source, final Type<LocalTime> destinationType) {
				if (source == null) {
					return null;
				}
				return new LocalTime(source.getTime());
			}

			@Override
			public Date convertFrom(final LocalTime source, final Type<Date> destinationType) {
				if (source == null) {
					return null;
				}
				return source.toDateTimeToday().toDate();
			}

		});

		/**
		 * Converter for handling Date to LocalDate conversion
		 */
		factory.getConverterFactory().registerConverter(new BidirectionalConverter<Date, LocalDate>() {
			@Override
			public LocalDate convertTo(final Date source, final Type<LocalDate> destinationType) {
				if (source == null) {
					return null;
				}
				return new LocalDate(source.getTime());
			}

			@Override
			public Date convertFrom(final LocalDate source, final Type<Date> destinationType) {
				if (source == null) {
					return null;
				}
				return source.toDate();
			}

		});

		/**
		 * Converter for handling Date to LocalDateTime conversion
		 */
		factory.getConverterFactory().registerConverter(new BidirectionalConverter<Date, LocalDateTime>() {
			@Override
			public LocalDateTime convertTo(final Date source, final Type<LocalDateTime> destinationType) {
				if (source == null) {
					return null;
				}
				return new LocalDateTime(source.getTime());
			}

			@Override
			public Date convertFrom(final LocalDateTime source, final Type<Date> destinationType) {
				if (source == null) {
					return null;
				}
				return source.toDate();
			}

		});

	}

	private void registerClassMaps(final DefaultMapperFactory factory) {
		//@formatter:off
		factory.registerClassMap(
			factory.classMap(Session.class, SessionDTO.class)
				.fieldMap("customer.id", "customerId").aToB().add()
				.fieldMap("invoiceRow.id", "invoiceRowId").aToB().add()
				.byDefault()
				.mapNulls(true)
				.toClassMap()
		);

		factory.registerClassMap(
			factory.classMap(Session.class, CalendarSessionDTO.class)
				.fieldMap("customer.id", "customerId").aToB().add()
				.customize(new CustomMapper<Session, CalendarSessionDTO>() {
					@Override
					public void mapAtoB(final Session session, final CalendarSessionDTO calendarSessionDTO, final MappingContext context) {
						// Set customer name
						calendarSessionDTO.setCustomerName(session.getCustomer().getFirstName() + " " + session.getCustomer().getLastName());
						// Set start and end date to ISO8601
						DateTimeFormatter fmt = ISODateTimeFormat.dateTime();

						calendarSessionDTO.setStart(fmt.print(session.getSessionDate().toLocalDateTime(session.getStartTime())));
						calendarSessionDTO.setEnd(fmt.print(session.getSessionDate().toLocalDateTime(session.getEndTime())));

					}}
				)
				.field("name", "name")
				.byDefault()
				.mapNulls(true)
				.toClassMap()
		);

		factory.registerClassMap(
			factory.classMap(Customer.class, CustomerDTO.class)
				.byDefault()
				.mapNulls(true)
				.toClassMap()
		);

		factory.registerClassMap(
			factory.classMap(Therapist.class, TherapistDTO.class)
				.byDefault()
				.mapNulls(true)
				.toClassMap()
		);

		factory.registerClassMap(
			factory.classMap(Invoice.class, InvoiceDTO.class)
				.fieldMap("customer.id", "customerId").aToB().add()
				.byDefault()
				.mapNulls(true)
				.toClassMap()
		);

		factory.registerClassMap(
			factory.classMap(Therapist.class, CompanyDTO.class)
				.byDefault()
				.mapNulls(true)
				.toClassMap()
		);

		factory.registerClassMap(
			factory.classMap(InvoiceRow.class, InvoiceRowDTO.class)
				.fieldMap("session.sessionDate", "date").aToB().add()
				.byDefault()
				.mapNulls(true)
				.toClassMap()
		);

	}

}
