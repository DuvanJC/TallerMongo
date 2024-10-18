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

    @Autowired
    private AuditService auditService;

    public List<Person> findAllPersons() {
        List<Person> persons = personRepository.findAll();
        auditService.logAudit("Person", "Se consultaron todas las personas");
        return persons;
    }

    public Person findPersonById(long id) {
        Person person = personRepository.findById(id).orElse(null);
        auditService.logAudit("Person", "Se consultó la persona con ID: " + id);
        return person;
    }

    public Person savePerson(Person person) {
        Person savedPerson = personRepository.save(person);
        auditService.logAudit("Person", "Se creó la persona: " + savedPerson.toString());
        return savedPerson;
    }

    public Person updatePerson(long id, Person person) {
        Person existingPerson = findPersonById(id);
        if (existingPerson != null) {
            person.setId(id);
            Person updatedPerson = personRepository.save(person);
            auditService.logAudit("Person", "Se actualizó la persona: " + updatedPerson.toString());
            return updatedPerson;
        }
        throw new RuntimeException("Persona no encontrada");
    }

    public void deletePerson(long id) {
        Person personToDelete = findPersonById(id);
        if (personToDelete != null) {
            personRepository.deleteById(id);
            auditService.logAudit("Person", "Se eliminó la persona con ID: " + id);
        } else {
            throw new RuntimeException("Persona no encontrada");
        }
    }
}
