package org.rakotulkki.resource.user;

import org.rakotulkki.model.user.Customer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author jkuittin
 */
@RestController
public class UserController {

	@ResponseBody
	@RequestMapping("/customers")
	public List<Customer> customers() {
		return Collections.emptyList();
	}

}
