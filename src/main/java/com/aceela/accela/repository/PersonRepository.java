package com.aceela.accela.repository;

import com.aceela.accela.common.PersonConstants;
import com.aceela.accela.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person,String> {

    Person save(Person person);

    Person getById(String id);

    @Modifying(clearAutomatically = true)
    @Query(PersonConstants.UPDATE_PERSON_DETAILS_BY_ID)
    void updatePersonDetails(@Param("id") String id,@Param("firstName") String firstName,@Param("lastName") String lastName);

    void deleteById(String id);

    List<Person> findAll();

    long count();
}
