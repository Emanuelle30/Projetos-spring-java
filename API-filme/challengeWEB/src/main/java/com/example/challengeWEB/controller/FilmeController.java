package com.example.challengeWEB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.challengeWEB.DTO.FilmeDTO;
import com.example.challengeWEB.converter.FilmeConverter;
import com.example.challengeWEB.service.FilmeService;
import com.example.challengeWEB.vo.FilmeOMDB;
import com.example.challengeWEB.vo.FilmeVO;

@RestController
@RequestMapping("/filme")
public class FilmeController {
	
	@Autowired
	private FilmeService filmeService;
	
	@Autowired
	private FilmeConverter filmeConverter;
	
	@GetMapping("/omdb/{tema}")
	public ResponseEntity<FilmeOMDB> getFilme(@PathVariable String tema) {
		try {
			FilmeOMDB filmeOMDB = filmeService.getFilme(tema);
			return ResponseEntity.status(HttpStatus.OK).body(filmeOMDB);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<FilmeVO> saveFilme(@RequestBody FilmeDTO filmeDTO) {
		try {
			FilmeVO filmeVO = filmeConverter.converteParaFilmeVO(filmeService.save(filmeDTO));
			
			addHateoas(filmeVO);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(filmeVO);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FilmeVO> getById(@PathVariable("id") Long id) {
		try {
			FilmeVO filmeVO = filmeConverter.converteParaFilmeVO(filmeService.getById(id));
			return ResponseEntity.ok(filmeVO);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	}
	
	private void addHateoas( FilmeVO filmeVO) {
		filmeVO.add(linkTo(methodOn(FilmeController.class).getById(filmeVO.getId())).
				withSelfRel());
	}

}
