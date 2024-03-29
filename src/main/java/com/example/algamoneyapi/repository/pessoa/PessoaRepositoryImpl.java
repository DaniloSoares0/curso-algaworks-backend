package com.example.algamoneyapi.repository.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.model.Pessoa_;
import com.example.algamoneyapi.repository.filter.PessoaFilter;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Pessoa> filtrar(PessoaFilter pFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		//criar restricoes
		Predicate[] predicates = criarRestricoes(pFilter,builder,root);
		criteria.where(predicates);

		TypedQuery<Pessoa> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePagincao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(pFilter));
	}
	
	private Predicate[] criarRestricoes(PessoaFilter pessoaFilter, CriteriaBuilder builder,Root<Pessoa> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if(pessoaFilter.getNome() != null){
			predicates.add(builder.like(builder.lower(root.get(Pessoa_.nome)),"%"+pessoaFilter.getNome().toLowerCase()+"%"));
		}

		if(pessoaFilter.getCodigo() != null){
			predicates.add(builder.equal(root.get(Pessoa_.codigo), pessoaFilter.getCodigo()));
		}
		

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	
	private void adicionarRestricoesDePagincao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private long total(PessoaFilter pessoaFilter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);

		Predicate[] predicates = criarRestricoes(pessoaFilter,builder,root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

}
