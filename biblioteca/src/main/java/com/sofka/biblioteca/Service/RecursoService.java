package com.sofka.biblioteca.Service;

import com.sofka.biblioteca.Repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecursoService {

    @Autowired
    RecursoRepository recursoRepository;


}
