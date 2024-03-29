package com.example.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.pessoa.PessoaRepositoryQuery;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery{

}
