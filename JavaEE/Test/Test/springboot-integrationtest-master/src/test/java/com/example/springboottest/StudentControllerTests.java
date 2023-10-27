package com.example.springboottest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTests {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    
    HttpHeaders headers = new HttpHeaders();
    
    @Test
    public void testStudentPostURL() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
          createURLWithPort("/students"), HttpMethod.POST, entity, String.class);
        
        System.out.println("Response String"+response.toString());
       
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        assertTrue(actual.contains("/students"));
        System.out.println("Actual String"+actual);
    }    

    @Test
    public void testRetrieveStudent() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
          createURLWithPort("/students/2"), HttpMethod.GET, entity, String.class);

        String expected = "{\"id\":2,\"name\":\"Arka Bhuiyan\",\"description\":\"Class 9\"}";
        
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
    
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
 
}

