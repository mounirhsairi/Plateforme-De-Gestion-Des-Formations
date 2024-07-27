package com.pfe.Back.model;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="programFormation")
public class programFormation extends AbstractEntity {

	@Column(name="Nom")
	private String nom ;
	@OneToMany(mappedBy = "program")
    private List<SessionFormation> sessionFormation;
}
