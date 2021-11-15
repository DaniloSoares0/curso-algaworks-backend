package com.example.algamoneyapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Builder
@Table(name="permissoes")
@EqualsAndHashCode(of = "codigo")
public class Permissoes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private String descricao;

}
