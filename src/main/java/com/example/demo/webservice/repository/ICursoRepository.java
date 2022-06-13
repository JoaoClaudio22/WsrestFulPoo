package com.example.demo.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.webservice.model.Cursos;


/*
 * A interface ICurso vai herdar os métodos do JPA Repository, contendo tudo que precisa para as operações do CRUD
 */
@Repository // Classe de persistencia que será gerenciada pelo Spring
public interface ICursoRepository extends JpaRepository<Cursos, Long>{

}
