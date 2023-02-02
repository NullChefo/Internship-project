package org.webserve.carservice.security;

import lombok.Data;

@Data
public class ClaimDTO {

	private boolean isValid;
	private String roles;
	private Long userId;

}
