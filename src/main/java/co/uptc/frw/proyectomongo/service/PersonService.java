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
        try {
            List<Person> persons = personRepository.findAll();
            auditService.logAudit("Person", "Se consultaron todas las personas");
            return persons;
        } catch (Exception e) {
            auditService.logAudit("Person", "Error al consultar todas las personas: " + e.getMessage());
            throw e;
        }
    }

    public Person findPersonById(long id) {
        try {
            Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Persona con id " + id + " no encontrada"));
            auditService.logAudit("Person", "Se consultó la persona con ID: " + id);
            return person;
        } catch (Exception e) {
            auditService.logAudit("Person", "Error al consultar la persona con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public Person savePerson(Person person) {
        try {
            Person savedPerson = personRepository.save(person);
            auditService.logAudit("Person", "Se creó la persona: " + savedPerson.toString());
            return savedPerson;
        } catch (Exception e) {
            auditService.logAudit("Person", "Error al crear la persona: " + e.getMessage());
            throw e;
        }
    }

    public Person updatePerson(long id, Person person) {
        try {
            Person existingPerson = findPersonById(id);
            if (existingPerson != null) {
                person.setId(id);
                Person updatedPerson = personRepository.save(person);
                auditService.logAudit("Person", "Se actualizó la persona: " + updatedPerson.toString());
                return updatedPerson;
            } else {
                throw new RuntimeException("Persona no encontrada");
            }
        } catch (Exception e) {
            auditService.logAudit("Person", "Error al actualizar la persona con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public void deletePerson(long id) {
        try {
            Person personToDelete = findPersonById(id);
            if (personToDelete != null) {
                personRepository.deleteById(id);
                auditService.logAudit("Person", "Se eliminó la persona con ID: " + id);
            } else {
                throw new RuntimeException("Persona no encontrada");
            }
        } catch (Exception e) {
            auditService.logAudit("Person", "Error al eliminar la persona con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }
}
