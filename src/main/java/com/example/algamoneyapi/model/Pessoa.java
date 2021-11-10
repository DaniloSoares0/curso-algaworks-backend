package com.example.algamoneyapi.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
@Table(name="pessoa")
public class Pessoa {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Size(min=2, max=30)
	@Column(name="nome", nullable = false, length = 30)
	private String nome;
	
	@NotNull
	@Column(name="ativo")
	private boolean ativo;
	
	@Embedded
	private Endereco endereco;
}
