package com.artsykov.fapi.security;

public class SecurityJwtConstants {
    static final long ACCESS_TOKEN_VALIDITY_SECONDS = 360000000;
    static final String SIGNING_KEY = "artsykov123sign";
    static final String TOKEN_PREFIX = "Bearer_";
    static final String HEADER_STRING = "Authorization";
}