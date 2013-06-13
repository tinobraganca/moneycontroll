package br.com.software.modelos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "formapagamento")
public class Cartao implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 3, message = "O nome não pode ter menos que 3 caracteres!")
	@Column(name = "nome", length = 37, unique = true)
	private String nome;
	
//	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
//	@JsonManagedReference("cartao")
//	private List<Transacao> Transacoes;
//
//	public List<Transacao> getTransacoes() {
//		return Transacoes;
//	}
//	
//	@JsonManagedReference("cartao")
//	public void setTransacoes(List<Transacao> transacoes) {
//		this.Transacoes = transacoes;
//	}

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
