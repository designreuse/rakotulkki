package org.rakotulkki.model.annotation;

import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/**
 * @author jkuittin
 */
@Target({ ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface AuthenticatedTherapist {
}

