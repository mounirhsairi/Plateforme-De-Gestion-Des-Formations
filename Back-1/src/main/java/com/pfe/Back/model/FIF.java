package com.pfe.Back.model;



import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true )
@Entity
public class FIF extends AbstractEntity {
	
	
	private String fileName;

	@Column(name = "FiFData", length=50000000)
    private String data;
    
    
    @ManyToOne
    @JoinColumn(name = "idOperateur")
    private Operateurs operateurs;
	
    
    
	
}
