package com.example.algamoneyapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.example.algamoneyapi.enums.TipoLancamento;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(of = "codigo")
@Table(name="lancamento")
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotNull
	private String descricao;

	@NotNull
	@Column(name="data_vencimento")
	private LocalDate dataVencimento;

	@NotNull
	@Column(name="data_pagamento")
	private LocalDate dataPagamento;

	@NotNull
	private BigDecimal valor;

	private String observacao;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipo;

	@NotNull
	@ManyToOne
	@JoinColumn(name="codigo_categoria")
	private Categoria categoria;

	@NotNull
	@ManyToOne
	@JoinColumn(name="codigo_pessoa")
	private Pessoa pessoa;
}
