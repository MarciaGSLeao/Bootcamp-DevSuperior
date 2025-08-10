package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
//	private CategoryDTO categoryDTO;
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll(){
		List<Category> listCategories = repository.findAll();
		return listCategories.stream().map(c -> new CategoryDTO(c)).toList();
	}
	
//		List<CategoryDTO> listDto = new ArrayList<>();
//		for(Category cat : listCategories) {
//			listDto.add(new CategoryDTO(cat));
//		}
//		return listDto;

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Registro não encontrado."));
		return new CategoryDTO(entity);
	}

}
