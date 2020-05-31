package com.codenation.modelo;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.springframework.data.annotation.CreatedDate;
import com.codenation.enumeracao.Level;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//Classe modelo da entidade utilizada para armazenar os logs de erros
@Data
@Setter
@Getter
@Entity
@Table(name = "logs")
public class LogEvento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;
	
	@Enumerated
	@Column(nullable = false)
	private Level level;
	
	@Column(name = "descricao_evento")
	private String descricaoEvento;
	
	@Column(name = "log_evento",nullable = false)
	@NotBlank
	private String logEvento;
	
	@Column
	private String origem;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime data;
	
	@Column
	@PositiveOrZero
	private Long quantidade;
		
}
