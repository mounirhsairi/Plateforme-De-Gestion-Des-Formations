package com.pfe.Back.model;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="DemandeDeRecrutement")
public class DemandeDeRecrutement extends AbstractEntity{
	
	
	@Column(name="MatriculeDemande")
	private String MatriculeDemande ;
	@Column(name="NombreDOperateurs")
	private Integer nombre ;
	@ManyToOne
    @JoinColumn(name = "idOperation")
	Operation  PosteAPouvoir ;
	@Column(name="Niveau")
	private String niveau ;
	@Column(name="DateDembaucheSouhaitee")
	private Date DateDembaucheSouhaitee ;
	@Column(name="TypeDePoste")
	private String type;
	@Column(name="motifDeRecrutement")
	private String motif ;
	@Column(name="HoraireDeTravail")
	private List<String> HoraireDeTravail ;
	@Column(name="Sexe")
	private String sexe ;
	@Column(name="Age")
	private String age ;
	@Column(name="Etat")
	private EtatDemandeDeFormation etat ;
	@OneToOne
    @JoinColumn(name = "idFormations")
    private Formations formation;
	
	@OneToMany(mappedBy = "demande")
	private List<Operateurs> operateurs ;
}
