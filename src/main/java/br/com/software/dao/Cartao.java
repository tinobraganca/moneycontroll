package br.com.software.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.internal.NotNull;

	@Entity
	@Table(name = "formapagamento") // @Table is optional, but "user" is a keyword in many SQL variants 
	public class Cartao implements java.io.Serializable {
		
		@Id // @Id indicates that this it a unique primary key
		@GeneratedValue (strategy = GenerationType.IDENTITY)// @GeneratedValue indicates that value is automatically generated by the server
	    @Column(name = "id", unique = true )
		private Long id;
		
		
		@NotNull
		@NotEmpty
		@Size(min=3, message="O nome não pode ter menos que 5 caracteres!")
		@Column(name = "nome" ,length = 37, unique = true)
		private String nome;


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getNome() {
			return nome;
		}


		public void setNome(String nome) {
			this.nome = nome;
		}

}
