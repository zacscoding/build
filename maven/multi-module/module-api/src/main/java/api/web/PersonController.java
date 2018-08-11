package api.web;

import common.entity.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zacconding
 * @Date 2018-08-11
 * @GitHub : https://github.com/zacscoding
 */
@RestController
@RequestMapping("/person/**")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger("module-api");

    private List<Person> persons = new ArrayList<>();

    @GetMapping(value = "")
    public List<Person> getPersons() {
        logger.info("getPersons() is called ");
        return persons;
    }

    @PostMapping(value = "")
    public Boolean savePerson(@RequestBody Person person) {
        this.persons.add(person);
        return Boolean.TRUE;
    }
}