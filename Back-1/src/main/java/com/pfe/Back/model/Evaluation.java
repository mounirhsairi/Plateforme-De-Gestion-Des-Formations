package com.pfe.Back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true )
@Entity
public class Evaluation extends AbstractEntity {

	
	
	@Column(name="EvaluationParAgentFormation")
	private String EvaluationParAgentFormation ;
	@Column(name="EvaluationParChefDeLigne")
	private String EvaluationParChefDeLigne ;
	@Column(name="EvaluationParIngénieurQualité")
	private String EvaluationParIngénieurQualité ;
	@Column(name="EvaluationParMaintenance")
	private String EvaluationParMaintenance ;
	@Column(name="EvaluationR&R")
	private String EvaluationParMaintenanceRAndR;
	@Column(name="EvaluationTotal")
	private String EvaluationTotal ;
	@ManyToOne
    @JoinColumn(name = "idOperateurs")
	private Operateurs operateurs ;
	@ManyToOne
    @JoinColumn(name = "idFormation")
	private Formations formation ;
	@ManyToOne
    @JoinColumn(name = "idOperation")
	private Operation operation ;
	
	@Column(name="ModifiedByIngenieurQualite")
	 private String modifiedByIngenieurQualite;

	 @Column(name="ModifiedByChefDeLigne")
	 private String modifiedByChefDeLigne;
}
