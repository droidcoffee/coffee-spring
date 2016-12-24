package com.coffee.service;

import java.util.List;

import com.coffee.model.Person;

public interface IPersonService {

    /**
     * 加载全部的person
     * @return
     */
    List<Person> loadPersons();
}