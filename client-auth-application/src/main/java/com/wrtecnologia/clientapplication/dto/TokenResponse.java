package com.wrtecnologia.clientapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
// @AllArgsConstructor //- Desabilitado por incompatibilidade (não funcional)
public class TokenResponse {

    private String accessToken;
    private Instant accessTokenExpiresAt;
    private String refreshToken;
    private Instant refreshTokenExpiresAt;
    private String clientRegistrationId;
    private String tokenType;
    private String clientId;
    private String clientSecret;

    public TokenResponse(String accessToken, Instant accessTokenExpiresAt, String refreshToken, Instant refreshTokenExpiresAt, String clientRegistrationId, String tokenType, String clientId, String clientSecret) {
        this.accessToken = accessToken;
        this.accessTokenExpiresAt = accessTokenExpiresAt;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresAt = refreshTokenExpiresAt;
        this.clientRegistrationId = clientRegistrationId;
        this.tokenType = tokenType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
     }
}
