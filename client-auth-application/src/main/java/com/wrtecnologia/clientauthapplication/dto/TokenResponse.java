package com.wrtecnologia.clientauthapplication.dto;

import java.time.Instant;

// @Data
// @AllArgsConstructor //- Desabilitado por incompatibilidade (não funcional)
public class TokenResponse {

    private String accessToken;
    private Instant accessTokenExpiresAt;
    private String refreshToken;
    private Instant refreshTokenExpiresAt;
    private String clientRegistrationId;
    private String tokenType;

    public TokenResponse(String accessToken, Instant accessTokenExpiresAt, String refreshToken, Instant refreshTokenExpiresAt, String clientRegistrationId, String tokenType) {
        this.accessToken = accessToken;
        this.accessTokenExpiresAt = accessTokenExpiresAt;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresAt = refreshTokenExpiresAt;
        this.clientRegistrationId = clientRegistrationId;
        this.tokenType = tokenType;
     }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Instant getAccessTokenExpiresAt() {
        return accessTokenExpiresAt;
    }

    public void setAccessTokenExpiresAt(Instant accessTokenExpiresAt) {
        this.accessTokenExpiresAt = accessTokenExpiresAt;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Instant getRefreshTokenExpiresAt() {
        return refreshTokenExpiresAt;
    }

    public void setRefreshTokenExpiresAt(Instant refreshTokenExpiresAt) {
        this.refreshTokenExpiresAt = refreshTokenExpiresAt;
    }

    public String getClientRegistrationId() {
        return clientRegistrationId;
    }

    public void setClientRegistrationId(String clientRegistrationId) {
        this.clientRegistrationId = clientRegistrationId;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

}
