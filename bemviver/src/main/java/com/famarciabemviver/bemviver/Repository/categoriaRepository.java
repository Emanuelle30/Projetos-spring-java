package com.famarciabemviver.bemviver.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.famarciabemviver.bemviver.model.categoria;



@Repository
public interface categoriaRepository extends JpaRepository<categoria, Long>{
	public List<categoria> findAllByDescricaoContainingIgnoreCase (String descricao);
}