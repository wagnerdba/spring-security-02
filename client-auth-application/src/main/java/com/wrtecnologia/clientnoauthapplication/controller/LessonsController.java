package com.wrtecnologia.clientnoauthapplication.controller;

import com.wrtecnologia.clientapplication.dto.TokenResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.time.Instant;

import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;

@RestController
public class LessonsController {


    private final RestClient restClient;
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public LessonsController(RestClient restClient, OAuth2AuthorizedClientManager authorizedClientManager) {
        this.restClient = restClient;
        this.authorizedClientManager = authorizedClientManager;
    }

    @GetMapping("/lessons")
    public String fetchLessons() {
        return restClient.get()
                .uri("http://localhost:8082/lessons")
                .attributes(clientRegistrationId("golf-client"))
                .retrieve()
                .body(String.class);
    }

    @GetMapping("/token")
    public TokenResponse fetchToken() {

        OAuth2AuthorizedClient authorizedClient = authorizedClientManager
                .authorize(OAuth2AuthorizeRequest.withClientRegistrationId("golf-client").principal("principal").build());

        if (authorizedClient != null && authorizedClient.getAccessToken() != null) {
            String accessToken = authorizedClient.getAccessToken().getTokenValue();
            Instant accessTokenExpiresAt = authorizedClient.getAccessToken().getExpiresAt();
            String refreshToken = authorizedClient.getRefreshToken() != null ? authorizedClient.getRefreshToken().getTokenValue() : null;
            Instant refreshTokenExpiresAt = authorizedClient.getRefreshToken() != null ? authorizedClient.getRefreshToken().getExpiresAt() : null;
            String clientRegistrationId = authorizedClient.getClientRegistration().getRegistrationId();
            String tokenType = authorizedClient.getAccessToken().getTokenType().getValue();
            String clientId = authorizedClient.getClientRegistration().getClientId();
            String clientSecret = authorizedClient.getClientRegistration().getClientSecret();
            return new TokenResponse(accessToken, accessTokenExpiresAt, refreshToken, refreshTokenExpiresAt, clientRegistrationId, tokenType, clientId, clientSecret);
        } else {
            return new TokenResponse("Token não disponível", null, null, null, null, null, null, null);
        }
    }
}