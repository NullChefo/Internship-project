package org.webserve.carservice.security;

public enum Roles {

	ROLE_USER, ROLE_DEV, ROLE_ADMIN, ROLE_BI, ROLE_CALL_CENTER;

	public String getAuthority() {
		return name();
	}

}

