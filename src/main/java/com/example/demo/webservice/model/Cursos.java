package com.example.demo.webservice.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data // Lombok
@AllArgsConstructor // Lombok
@NoArgsConstructor // Lombok
@Entity //A classe Curso será uma Entidade relacional
public class Cursos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING) // Salva no BD uma String com o nome da constante;
	private CargaHoraria cargaHoraria;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;
		
	
}
