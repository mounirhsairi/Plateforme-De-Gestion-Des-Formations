package com.pfe.Back.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true )
@Entity
@Table(name="Formations")
public class Formations extends AbstractEntity{
	
	
	@OneToOne(mappedBy = "formation")
    private DemandeDeRecrutement demandeFormation;
	
	@OneToOne(mappedBy = "formationPoly")
    private DemandeDePolyvalence demandePoly;


	@Column(name="MatriculeFormation")
	private String MatriculeFormation ;
	@Column(name="TypeDeFormation")
	private String TypeDeFormation ;
	
	@OneToMany(mappedBy = "formation")
	private List<Evaluation> evalution ;
	
}
