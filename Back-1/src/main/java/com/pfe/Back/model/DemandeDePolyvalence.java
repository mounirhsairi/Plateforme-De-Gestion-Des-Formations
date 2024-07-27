package com.pfe.Back.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
@Table(name="DemandeDePolyvalence")
public class DemandeDePolyvalence extends AbstractEntity  {
	
	@Column(name="MatriculeDemande")
	private String MatriculeDemande ;
	@OneToMany(mappedBy = "demande")
    private List<DemandeDePolyvalenceData> demandDataList ;
	@Column(name="Etat")
	private EtatDemandeDeFormation etat ;
	@OneToOne
    @JoinColumn(name = "idFormations")
    private Formations formationPoly;
	
	/* @Column(name="DateDebut")
	 private String dateDebut;

	  @Column(name="DateFin")
	  private String dateFin ;*/

}
