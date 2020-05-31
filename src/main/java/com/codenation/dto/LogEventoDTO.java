package com.codenation.dto;

import java.time.LocalDateTime;

import com.codenation.enumeracao.Level;

public class LogEventoDTO {

	private Double id;
	
	private Level level;
	
	private String descricaoEvento;
	
	private String origem;

	private LocalDateTime data;

	private Double quantidade;
}
