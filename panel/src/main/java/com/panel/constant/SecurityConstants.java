package com.panel.constant;

public class SecurityConstants {
	public static final String HEADER_VALUE = "Authorization";
	public static final String TOKEN_MISSING_ERROR = "JWT Token is missing";
	public static final String INVALID_TOKEN_ERROR = "JWT Token is incorrect";
	public static final String TOKEN_PREFIX = "Token ";
	public static final int TOKEN_PREFIX_LENGHT = 6;
	public static final String URL_TO_FILTER = "/admin/**";
	public static final String URL_WITH_TOKEN = "**/admin/**";
	public static final String SEND_UNAUTHORIZED_MESSAGE = "UNAUTHORIZED";
	public static final String SECRET_KEY = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 000060000; // in milliseconds//864_000_000(10 days)

}
