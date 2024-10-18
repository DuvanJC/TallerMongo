package co.uptc.frw.proyectomongo.repository;


import co.uptc.frw.proyectomongo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
