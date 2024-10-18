package co.uptc.frw.proyectomongo.service;

import co.uptc.frw.proyectomongo.model.Person;
import co.uptc.frw.proyectomongo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public Person findPersonById(long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(long id, Person person) {
        Person personId = findPersonById(id);
        if (personId != null) {
            person.setId(id);
            return personRepository.save(person);
        }
        throw new RuntimeException("Person not found");
    }

    public void deletePerson(long id) {
        personRepository.deleteById(id);
    }
}
