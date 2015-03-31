package org.rakotulkki.repository;

import org.rakotulkki.model.hibernate.Company;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jkuittin
 */
public interface CompanyRepository extends CrudRepository<Company, Long> {

}
