package com.example.algamoneyapi.model;

import javax.persistence.Column;
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
@Table(name="categoria")
public class Categoria {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Size(min =3, max=20)
	@Column(name="nome", nullable = false)
	private String nome;
	
}
