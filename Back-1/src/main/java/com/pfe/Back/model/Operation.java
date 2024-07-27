package com.pfe.Back.model;

import java.util.List;
import java.util.Set;

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
@Table(name="Operation")
public class Operation extends AbstractEntity {
	
	@Column(name = "OperationName")
	private String OperationName ;
	@OneToMany(mappedBy = "operation")
	private List<OperateurOperationAssignment> assignments;
	
	@ManyToOne
    @JoinColumn(name = "idLigne")
    private Ligne ligne ;
	@OneToMany(mappedBy="NouvelleOperation")
	private List<DemandeDePolyvalenceData> NouvelleOperation ;
	@OneToMany(mappedBy="PosteAPouvoir")
	private List<DemandeDeRecrutement> PosteAPouvoir ;
	@OneToMany(mappedBy = "operation")
	private List<Evaluation> evalution ;
}
