package com.example.demo.webservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.webservice.model.Cursos;
import com.example.demo.webservice.repository.ICursoRepository;

import lombok.AllArgsConstructor;

@RestController 
@AllArgsConstructor //Faz a injeção de dependencias toda vez que chamar o cursoRepository (Opção para o @Autowired)
public class CursoController {
	
	ICursoRepository cursoRepository;
	
	/*
	 * Método para pegar todos os cursos cadastrados
	 */
	@GetMapping("/cursos") 
	@ResponseStatus(HttpStatus.OK)
	public List<Cursos> showAllCourses(){ 
		
		
		List<Cursos> courseList = this.cursoRepository.findAll();
		
		ModelAndView mv = new ModelAndView("index.html");
		mv.addObject("Cursos",courseList);
		
		return cursoRepository.findAll();
	}

	
	/*
	 * Método para pegar o curso pelo ID
	 */
	@GetMapping("/cursos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cursos getCourseById(@PathVariable Long id) { 
		return cursoRepository.findById(id).get();
	}
	
	
	/*
	 * Adicionar o curso que vem no corpo da requisição
	 */
	@PostMapping("/cursos")
	@ResponseStatus(HttpStatus.CREATED)
	public Cursos addCourse(@RequestBody Cursos curso) {  
		return cursoRepository.save(curso);
	}
	
	
	/*
	 * Edita o curso pelo ID
	 */
	@PutMapping("/cursos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cursos updateCourse(@RequestBody Cursos newCurso, @PathVariable Long id) { 
		/*
		 * Recebe o curso que vai ser editado,faz o mapeamento e atualiza cada item
		 */
		return cursoRepository.findById(id) 
				.map(curso -> {
			        curso.setTitulo(newCurso.getTitulo());
			        curso.setCargaHoraria(newCurso.getCargaHoraria());
					curso.setDataInicio(newCurso.getDataInicio());
			        return cursoRepository.save(curso);
			      })
				/*
				 * Caso o Id nao exista, ele cria um novo curso;
				 */
			      .orElseGet(() -> {  
				        return cursoRepository.save(newCurso);
				  	});
			      
			  }
	
	/*
	 * Deletar curso de acordo com o id
	 */
	@DeleteMapping("/cursos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void deleteCourse(@PathVariable Long id) {
		cursoRepository.deleteById(id);
	}
}
