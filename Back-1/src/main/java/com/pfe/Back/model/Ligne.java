package com.pfe.Back.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="Ligne")
public class Ligne extends AbstractEntity{

	@Column(name="NomLigne")
	private String nomLigne ;
	@OneToMany(mappedBy = "ligne")
    private List<Operation> operations;
	@OneToMany(mappedBy="ligneActuel")
	private List<DemandeDePolyvalenceData> ligneActuel ;
	@OneToMany(mappedBy="nouvelleLigne")
	private List<DemandeDePolyvalenceData> nouvelleLigne ;
	@OneToMany(mappedBy="ligne")
	@JsonIgnore
	List<UserLigneAssignement>userLigneAssignement ;
	
	}
