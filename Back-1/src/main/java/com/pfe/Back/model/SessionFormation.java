package com.pfe.Back.model;

import java.util.Date;
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
@Table(name="SessionFormation")
public class SessionFormation extends AbstractEntity{
	
	@Column(name="dateDebut")
	private String dateDebut ;
	@Column(name="dateFin")
	private String dateFin ;
	@Column(name="theme")
	private String theme ;
	@OneToMany(mappedBy = "session")
	private List<ListProgramme> programme ;
	@Column(name="type")
	private String type ;
	@Column(name="etat")
	private String etat ;
	@Column(name="Resp")
	private String Resp ;
	@Column(name="Doc")
	private String Doc ;
	@Column(name="duree")
	private Integer duree ;
	@ManyToOne
    @JoinColumn(name = "idProgram")
    private programFormation program ;
	@Column(name="partie")
	private String partie ;
	
	

}
