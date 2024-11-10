# OAuth2 com Spring Boot 3.3+ 

Uma implementação OAuth2 robusta usando Spring Boot 3.3+. Este projeto demonstra uma configuração completa do OAuth2 com um servidor de autorização, servidor de recursos e aplicativos cliente.

Em particular, este aplicativo está exibindo o novo suporte RestClient para OAuth2 no Spring Security 6.4.

https://spring.io/blog/2024/10/28/restclient-support-for-oauth2-in-spring-security-6-4

## Visão Geral do Projeto

O projeto consiste em três componentes principais:

1. **Authorization Server** (Port 9000) - Lida com autenticação e emite tokens OAuth2
2. **Resource Server** (Port 8082) - Provê os endpoints da aplicação
3. **Client Applications**:
    - OAuth2 Client (Implementação com Spring Security)
    - No-Auth Client (RestClient sem autorização)

## Arquitetura

```mermaid
graph TD
    A[Client Application] -->|OAuth2 Token Request| B[Authorization Server]
    B -->|Issues Token| A
    A -->|Request with Token| C[Resource Server]
    C -->|Validates Token with| B
    C -->|Returns Protected Resources| A
```

## Requerimentos do Projeto

- Java 23
- Spring Boot 3.3.5+
- Maven 3.6+
- Spring Security 6.4+

## Características chave

- Implementação do servidor de autorização OAuth2
- Autenticação baseada em token JWT
- Servidor de recursos com endpoints protegidos
- Implementação do fluxo de credenciais do cliente
- RestClient com suporte OAuth2

## Executando a aplicação

### Configurando o Authorization Server

1. Inicie o authorization server:

```bash
cd authorization-server
mvn spring-boot:run
```

O servidor iniciará na porta 9000 com a seguinte configuração:

```yaml
spring:
  security:
    oauth2:
      authorizationserver:
        issuer: http://localhost:9000
```

### Configurando o Resource Server

1. Inicie o resource server:

```bash
cd resource-server
mvn spring-boot:run
```

O resource server é executado na porta 8082 e está configurado para validar tokens com o servidor de autorização:

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://localhost:9000
```

### Aplicações Cliente

#### OAuth2 Client Application

Configuration example:

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          golf-client:
            client-id: golf-client
            client-secret: golf-secret
            authorization-grant-type: client_credentials
            scope: read
```

#### Usando a REST API

Para acessar recursos protegidos:

```java
@RestController
public class LessonsController {
    private final RestClient restClient;

    @GetMapping("/lessons")
    public String fetchLessons() {
        return restClient.get()
                .uri("http://localhost:8082/lessons")
                .attributes(clientRegistrationId("golf-client"))
                .retrieve()
                .body(String.class);
    }
}
```

## Configuração de segurança

### Authorization Server

O servidor de autorização está configurado com registro de cliente na memória:

```java
@Bean
public RegisteredClientRepository registeredClientRepository() {
    RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("golf-client")
            .clientSecret(passwordEncoder().encode("golf-secret"))
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .scope("read")
            .build();
    return new InMemoryRegisteredClientRepository(registeredClient);
}
```

### Resource Server

O resource server está configurado para exigir autenticação para todas as solicitações:

```java
@Configuration
@EnableWebSecurity
public class ResourceServerConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(Customizer.withDefaults())
            );
        return http.build();
    }
}
```

## API Endpoints

### Lessons API

`GET /lessons` - Recupera registros disponíveis

Examplo de response:
```json
[
  {
    "title": "Beginner Golf Basics",
    "description": "An introduction to the fundamentals of golf.",
    "instructor": "John Doe",
    "schedule": "2024-11-05T10:00:00"
  }
]
```

## Testando

O projeto inclui testes JUnit para cada componente. Execute testes usando:

```bash
mvn test
```

## Error Handling

Os aplicativos cliente incluem tratamento abrangente de erros para problemas relacionados ao OAuth2:

```java
.defaultStatusHandler(HttpStatusCode::is4xxClientError, (request, response) -> {
    if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, 
            "Unauthorized access to lessons API");
    }
    throw new ResponseStatusException(response.getStatusCode(), 
        "Client error occurred");
})
```
## Youtube Video tutorial (original)

https://www.youtube.com/watch?v=nFKcJDpUuZ8

## Repo Github (original)

https://github.com/danvega/golf-scheduler?tab=readme-ov-file