package com.coffee.service.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffee.mapper.PersonMapper;
import com.coffee.model.Person;
import com.coffee.service.IPersonService;

@Service("personService")
public class PersonServiceImpl implements IPersonService {
    
    private PersonMapper personMapper;

    public PersonMapper getPersonMapper() {
        return personMapper;
    }
    @Autowired
    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Override
    public List<Person> loadPersons() {
    	LoggerFactory.getLogger("test").error("---test----");
        return personMapper.queryAll();
    }
    
}