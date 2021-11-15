package com.example.algamoneyapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="usuario")
@EqualsAndHashCode(of = "codigo")
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private String email;
	
	private String senha;

	private int active;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuario_permissao", joinColumns = @JoinColumn(name="codigo_usuario"),
	inverseJoinColumns = @JoinColumn(name="codigo_permissao"))
	private List<Permissoes> permissions;
	
}
