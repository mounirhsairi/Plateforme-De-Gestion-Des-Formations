package com.pfe.Back.model;


import java.time.Instant;
import java.util.List;

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
@Table(name="OperateurOperationAssignment")
public class OperateurOperationAssignment extends AbstractEntity {
	@ManyToOne
    @JoinColumn(name = "idOperateurs")
    private Operateurs operateurs;

    @ManyToOne
    @JoinColumn(name = "idOpreation")
    private Operation operation;
    
    
    private String codeFormation ;
    private String methodeSuivi ;

    private String documentIdentification ;


    private Instant startDate;

    private Instant endDate;
}
