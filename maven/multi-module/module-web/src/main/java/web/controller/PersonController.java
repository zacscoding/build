package web.controller;

import common.entity.Person;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author zacconding
 * @Date 2018-08-11
 * @GitHub : https://github.com/zacscoding
 */
@Controller
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger("module-web");

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/persons")
    @ResponseBody
    public List<Person> getPersons() {
        logger.info("## request persons.");
        StringBuilder url = new StringBuilder("http://localhost:8989/person");

        ResponseEntity<List<Person>> response = restTemplate.exchange(
            url.toString(),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Person>>() {
            });

        return response.getBody();
    }

    @PostMapping("/person")
    @ResponseBody
    public Boolean savePerson(@RequestBody Person person) {
        logger.info("## request save person. person : {}", person);
        String url = "http://localhost:8989/person";

        ResponseEntity<Boolean> result = restTemplate.postForEntity(url, person, Boolean.class);

        return result.getBody();
    }
}
