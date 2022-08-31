package com.example.entregable02.Controllers;

import com.example.entregable02.Entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+ port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void FindAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
    }

    @DisplayName("Validar la busqueda para el Id 1")
    @Test
    void findById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/1",Laptop.class);
        System.out.println(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void Save() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "fabricante": "Mac Book Pro",
                    "memoria": "12GB",
                    "disco": "256GB",
                    "createAt": "22:27:45"
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, httpHeaders);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request,Laptop.class);

        Laptop laptop = response.getBody();

        assertEquals(1L,laptop.getId());
        assertEquals("Mac Book Pro",laptop.getFabricante());

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }
}