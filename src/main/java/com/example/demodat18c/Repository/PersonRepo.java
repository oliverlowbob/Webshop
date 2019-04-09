package com.example.demodat18c.Repository;

import com.example.demodat18c.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

//annoter som repository - alt i databasehåndtering
@Repository
public class PersonRepo {

    // brug JdbcTemplate
    @Autowired
    JdbcTemplate template;

    public List<Person> fetchAllPersons(){

        //her skal der hentes personer fra databasen
        String sql = "SELECT * FROM person";
        // rowmapper mapper rækker i sql-query'en til en liste af Person
        RowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
        // udfør sql query
        return template.query(sql, rowMapper);

        //List<Person> persons = new ArrayList<>();
        //persons.add(new Person("Kurt", "Hansen"));
        //persons.add(new Person("Donald", "Trump"));
        //persons.add(new Person("Hillary", "Clinton"));
        //persons.add(new Person("Benie", "Sanders"));
        //return persons;
    }

    public void addPerson(Person person){
        // prepared statement
        String sql = "INSERT INTO person (id, first_name, last_name) VALUES (?, ?, ?)";
        // udfør insert med jdbc template
        template.update(sql, person.getId(), person.getFirst_name(), person.getLast_name());
    }

}
