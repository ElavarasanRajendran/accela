package com.aceela.accela.repository;

import com.aceela.accela.data.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testGetById() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");
        entityManager.persist(person);
        Person person1 = personRepository.getById("ID1");
        assertEquals("ID1",person1.getId());
        assertEquals("Steve",person1.getFirstName());
        assertEquals("Smith",person1.getLastName());
    }

    @Test
    public void testUpdatePersonDetails() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");
        entityManager.persist(person);
        personRepository.updatePersonDetails("ID1", "Joe", "Root");
        Person person1 = personRepository.getById("ID1");
        assertEquals("ID1",person1.getId());
        assertEquals("Joe",person1.getFirstName());
        assertEquals("Root",person1.getLastName());
    }

    @Test
    public void testSave() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");
        entityManager.persist(person);

        Person newPerson = new Person();
        newPerson.setId("ID2");
        newPerson.setFirstName("Joe");
        newPerson.setLastName("Root");

        personRepository.save(newPerson);
        Person person1 = personRepository.getById("ID2");
        assertEquals("ID2",person1.getId());
        assertEquals("Joe",person1.getFirstName());
        assertEquals("Root",person1.getLastName());
    }

    @Test
    public void testDeleteById() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");
        entityManager.persist(person);
        personRepository.deleteById("ID1");
        assertNull(personRepository.getById("ID1"));
    }

    @Test
    public void testCount() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");
        entityManager.persist(person);

        Person newPerson = new Person();
        newPerson.setId("ID2");
        newPerson.setFirstName("Joe");
        newPerson.setLastName("Root");
        entityManager.persist(newPerson);

        assertEquals(2, personRepository.count());
    }

    @Test
    public void testFindAll() {
        Person person = new Person();
        person.setId("ID1");
        person.setFirstName("Steve");
        person.setLastName("Smith");
        entityManager.persist(person);

        Person newPerson = new Person();
        newPerson.setId("ID2");
        newPerson.setFirstName("Joe");
        newPerson.setLastName("Root");
        entityManager.persist(newPerson);

        List<Person> personList = personRepository.findAll();
        assertEquals(2, personList.size());

        assertEquals(personList.get(0).getId(), person.getId());
        assertEquals(personList.get(0).getFirstName(), person.getFirstName());
        assertEquals(personList.get(0).getLastName(), person.getLastName());

        assertEquals(personList.get(1).getId(), newPerson.getId());
        assertEquals(personList.get(1).getFirstName(), newPerson.getFirstName());
        assertEquals(personList.get(1).getLastName(), newPerson.getLastName());
    }
}
