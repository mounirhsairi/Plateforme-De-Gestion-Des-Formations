package com.pfe.Back.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateurFromExcel {
	private String  matricule ;
	private String nomOperateur ;
	private Date date ;
	private String nomLigne ;
	private String Opération ;

}
