package com.pfe.Back.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="DemandeDePolyvalenceData")
public class DemandeDePolyvalenceData extends AbstractEntity {
	
	@ManyToOne
    @JoinColumn(name = "idLigneActuel")
    private Ligne ligneActuel;
	@ManyToOne
    @JoinColumn(name = "idOperateur")
    private Operateurs operateurs;
	@ManyToOne
    @JoinColumn(name = "idNouvelleLigne")
    private Ligne nouvelleLigne;
	@ManyToOne
    @JoinColumn(name = "idNouvelleOperation")
    private Operation NouvelleOperation;

	@Column(name="DelaiLimite")
	private Date DelaiLimite ;
	@Column(name="CauseDeFormation")
	private String cause ;
	@Column(name="Objectifs")
	private String Objectifs ;
	
	@ManyToOne
    @JoinColumn(name = "idDemandeDePolyvalence")
    private DemandeDePolyvalence demande;
	
	@Column(name="DateDebut")
	 private String dateDebut;

	  @Column(name="DateFin")
	  private String dateFin ;
    

}
