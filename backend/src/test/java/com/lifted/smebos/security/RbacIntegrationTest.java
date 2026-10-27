package com.lifted.smebos.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RbacIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port;
    }

    @Test
    void ownerCanAccessAdminApi() throws Exception {
        String token = loginAndGetToken("owner");

        ResponseEntity<String> response =
                getWithToken("/api/admin", token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void managerCannotAccessAdminApi() throws Exception {
        String token = loginAndGetToken("manager");

        ResponseEntity<String> response =
                getWithToken("/api/admin", token);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void staffCannotAccessAdminApi() throws Exception {
        String token = loginAndGetToken("staff");

        ResponseEntity<String> response =
                getWithToken("/api/admin", token);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void ownerCanAccessSalesReportApi() throws Exception {
        String token = loginAndGetToken("owner");

        ResponseEntity<String> response =
                getWithToken("/api/reports/sales", token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void managerCanAccessSalesReportApi() throws Exception {
        String token = loginAndGetToken("manager");

        ResponseEntity<String> response =
                getWithToken("/api/reports/sales", token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void staffCannotAccessSalesReportApi() throws Exception {
        String token = loginAndGetToken("staff");

        ResponseEntity<String> response =
                getWithToken("/api/reports/sales", token);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void unauthenticatedUserCannotAccessAdminApi() {
        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        baseUrl + "/api/admin",
                        String.class
                );

        assertTrue(
                response.getStatusCode().is4xxClientError(),
                "An unauthenticated request must not access the Admin API."
        );

        assertFalse(
                response.getStatusCode().is2xxSuccessful(),
                "An unauthenticated request must not receive a successful response."
        );
    }

    private String loginAndGetToken(String username) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> credentials = Map.of(
                "username", username,
                "password", "Password123!"
        );

        HttpEntity<Map<String, String>> request =
                new HttpEntity<>(credentials, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        baseUrl + "/api/auth/login",
                        request,
                        String.class
                );

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode(),
                "Login failed for user: " + username
        );

        assertNotNull(response.getBody());

        JsonNode json = objectMapper.readTree(response.getBody());
        JsonNode tokenNode = json.get("token");

        assertNotNull(tokenNode, "Login response did not contain a token.");
        assertFalse(tokenNode.asText().isBlank());

        return tokenNode.asText();
    }

    private ResponseEntity<String> getWithToken(
            String path,
            String token
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        return restTemplate.exchange(
                baseUrl + path,
                HttpMethod.GET,
                request,
                String.class
        );
    }
}
