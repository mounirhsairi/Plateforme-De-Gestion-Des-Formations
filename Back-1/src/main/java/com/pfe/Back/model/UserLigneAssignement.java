package com.pfe.Back.model;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="UserLigneAssignement")
public class UserLigneAssignement extends AbstractEntity{
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idLigne")
    private Ligne ligne;
	public Integer getIdLigne() {
        return (ligne != null) ? ligne.getId() : null;
    }
	
	
}
