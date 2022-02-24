package com.stanli.libraryapplication.repository;

import com.stanli.libraryapplication.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
