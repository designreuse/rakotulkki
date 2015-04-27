package org.rakotulkki.model.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author jkuittin
 */
public class TherapistDTO implements UserDetails {

	private static final List<GrantedAuthority> defaultAuthorities = new ArrayList<>();

	static {
		defaultAuthorities.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return "ROLE_THERAPIST";
			}
		});
	}

	private Long id;

	private String name;

	private String email;

	private String password;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return defaultAuthorities;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
}
