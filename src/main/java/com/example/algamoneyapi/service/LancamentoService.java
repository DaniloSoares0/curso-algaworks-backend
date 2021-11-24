package com.example.algamoneyapi.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.LancamentoRepository;
import com.example.algamoneyapi.repository.PessoaRepository;
import com.example.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

import javassist.NotFoundException;

@Service
public class LancamentoService   {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Lancamento save(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).orElse(null);
		if(pessoa == null || pessoa.isAtivo() == false) {
			throw new PessoaInexistenteOuInativaException();
		}
		return lancamentoRepository.save(lancamento);
	}

	public Lancamento atualizar(Long codigo, @Valid Lancamento lancamento) throws Exception {
		
		Lancamento lancamentoBase = lancamentoRepository
				.findById(codigo)
				.orElseThrow(() -> new NotFoundException("Lancamento não encontrado"));
		
		if(!lancamento.getPessoa().equals(lancamentoBase.getPessoa())) {
			validarPessoa(lancamento);
		}
		
		BeanUtils.copyProperties(lancamento, lancamentoBase,"codigo");
		
		return lancamentoRepository.save(lancamentoBase);
	}

	private void validarPessoa(Lancamento lancamento) throws NotFoundException {

		Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo())
				.orElseThrow(()-> new NotFoundException("Pessoa nao encontrada"));
		
		if(pessoa.isAtivo() == false);
		   throw new PessoaInexistenteOuInativaException();
			
	}

	
	
}
