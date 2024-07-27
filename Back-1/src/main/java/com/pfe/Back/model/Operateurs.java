package com.pfe.Back.model;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name="Operateurs")
public class Operateurs extends AbstractEntity {
	@Column(name = "matricule")
	private String matricule ;
	@Column (name = "NomOperateur")
	 private String NomOperateur ;
	@Column (name = "telephone")
	private String telephone;
	@Column(name="Etat")
	private String Etat;
	@Column(name="UAP")
	private String UAP;
	@Column(name="Chaine")
	private String chaine;

	@Column(name = "image", length=50000000)
    private String image;
	@Column(name="Qualification")
	private String Qualification ;
	@OneToMany(mappedBy = "operateurs")
    private List<FIF> pdfFiles;
	@OneToMany(mappedBy = "operateurs")
	private List<OperateurOperationAssignment> assignments;
	@OneToMany(mappedBy = "operateurs")
	private List<DemandeDePolyvalenceData> data ;
	@OneToMany(mappedBy = "operateurs")
	private List<Evaluation> evalution ;
	@ManyToOne
    @JoinColumn(name = "idDemande")
    private DemandeDeRecrutement demande;
	
	
}
