package co.uptc.frw.proyectomongo.controller;

import co.uptc.frw.proyectomongo.model.Person;
import co.uptc.frw.proyectomongo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Persons")
public class PersonController {

    @Autowired
    private PersonService personService;


    @GetMapping
    public List<Person> getAllPersons() {
        return personService.findAllPersons();
    }


    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") long id) {
        return personService.findPersonById(id);
    }


    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }


    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    public void deletePersona(@PathVariable("id") long id) {
        personService.deletePerson(id);
    }
}
