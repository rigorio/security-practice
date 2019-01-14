package rigor.io.securitypractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@SpringBootApplication
public class SecurityPracticeApplication {


  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(SecurityPracticeApplication.class, args);
  }

}

@RestController
class IsbnRestController {

  private final RestTemplate restTemplate;

  IsbnRestController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @GetMapping("/books/{isbn}")
  public ResponseEntity<?> byIsbn(@PathVariable String isbn) {
    ResponseEntity<String> exchange = restTemplate.exchange("https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn, HttpMethod.GET,
                                                            null, String.class);
    return new ResponseEntity<>(exchange.getBody(), HttpStatus.OK);

  }
}

@Component
class UuidService {
  public String buildUuid() {
    return UUID.randomUUID().toString();
  }
}

