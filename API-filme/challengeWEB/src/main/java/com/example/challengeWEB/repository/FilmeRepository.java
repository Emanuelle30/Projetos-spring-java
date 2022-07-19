package com.example.challengeWEB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.challengeWEB.model.Filme;


@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{

}
