package com.codenation.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "logs")
@EntityListeners(AuditingEntityListener.class)
//Classe modelo da entidade utilizada para armazenar os logs de erros
public class LogEvento implements Serializable {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "descricao_evento")
	private String descricaoEvento;
	
	@NotBlank
	@Column(name = "log_evento",nullable = false)
	private String logEvento;
	
	@Column
	private String origem;
	
	@CreatedDate
	@Column(columnDefinition = "timestamp default current_timestamp")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime data;	

	@Column
	@PositiveOrZero
	private Long quantidade;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoEvento() {
		return descricaoEvento;
	}

	public void setDescricaoEvento(String descricaoEvento) {
		this.descricaoEvento = descricaoEvento;
	}

	public String getLogEvento() {
		return logEvento;
	}

	public void setLogEvento(String logEvento) {
		this.logEvento = logEvento;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
		
}
