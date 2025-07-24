package com.turnat.TurnAT.dto;

import com.turnat.TurnAT.models.entities.Direccion;

public record DireccionDTO(
		String localidad,
	    String calle,
	    int altura,
	    String depto
		
		
){}