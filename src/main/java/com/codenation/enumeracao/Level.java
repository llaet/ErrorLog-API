package com.codenation.enumeracao;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Level {

	ERROR("Error"), WARNING("Warning"), INFO("Info");
	
	private String status;
	
	private Level(String status) {
		this.status = status;
	}
	
	public String retornaStatus() {
		return status;
	}
}
