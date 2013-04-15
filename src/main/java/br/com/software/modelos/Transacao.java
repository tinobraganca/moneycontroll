package br.com.software.modelos;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

@Entity
@Table(name = "transacao")
public class Transacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 2, message = "O nome não pode ter menos que 2 caracteres!")
	@Column(name = "descricao", length = 100, unique = true)
	private String descricao;

	@Column(name = "data")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yy")
	// iso=ISO.DATE)
	private Calendar data;

	@NumberFormat(style = Style.NUMBER)
	@Column(name = "valor")
	private BigDecimal valor;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cartao_id")
	private Cartao cartao;
	
	@NumberFormat(style = Style.NUMBER)
	@Column(name = "tipo")
	private int tipo = 0;

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Calendar getData() {
		return data;
	}
	public String getdataFormatada() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		return df.format(this.data.getTime());
		
	}


	public void setData(Calendar data) {
		this.data = data;
	}

}
