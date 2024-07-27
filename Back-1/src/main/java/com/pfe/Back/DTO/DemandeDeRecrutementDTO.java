package com.pfe.Back.DTO;

import java.sql.Date ;
import java.time.Instant;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.Back.model.DemandeDeRecrutement;
import com.pfe.Back.model.EtatDemandeDeFormation;
import com.pfe.Back.model.Formations;
import com.pfe.Back.model.Operateurs;
import com.pfe.Back.model.Operation;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DemandeDeRecrutementDTO {
    private Integer nombre;
    private Integer idOperation;
    private String niveau;
    private Date dateDembaucheSouhaitee;
    private String type;
    private String motif;
    private List<String> horaireDeTravail;
    private String sexe;
    private String age;
    private EtatDemandeDeFormation etat;
    private Integer idFormations;
	private String MatriculeDemande ;
	 private String lastModifiedBy ;
		private String creationDate ;

	private Integer id ;
    @JsonIgnore
    private Formations formation ;
    @JsonIgnore
	private List<Operateurs> operateurs ;

    public static DemandeDeRecrutementDTO fromEntity(DemandeDeRecrutement demande) {
        return DemandeDeRecrutementDTO.builder()
        		.id(demande.getId())
                .nombre(demande.getNombre())
                .idOperation(demande.getPosteAPouvoir() != null ? demande.getPosteAPouvoir().getId() : null)
                .niveau(demande.getNiveau())
                .dateDembaucheSouhaitee(demande.getDateDembaucheSouhaitee())
                .type(demande.getType())
                .age(demande.getAge())
                .motif(demande.getMotif())
                .horaireDeTravail(demande.getHoraireDeTravail())
                .sexe(demande.getSexe())
                .etat(demande.getEtat())
                .MatriculeDemande(demande.getMatriculeDemande())
                .idFormations(demande.getFormation() != null ? demande.getFormation().getId() : null)
                //.forma(demande.getFormation())
                .operateurs(demande.getOperateurs())
                .lastModifiedBy(demande.getLastModifiedBy())
                .creationDate(demande.getCreationDate())
                .build();
    }

    public static DemandeDeRecrutement toEntity(DemandeDeRecrutementDTO dto) {
        DemandeDeRecrutement demande = new DemandeDeRecrutement();
        Operation operation = new Operation();
        List<Operateurs> operateurs = new ArrayList<Operateurs>();
        for(Operateurs operateur :operateurs) {
        operateur.setDemande(demande);
        }
        demande.setId(dto.getId());
        demande.setOperateurs(operateurs);
        demande.setLastModifiedBy(dto.getLastModifiedBy());
        demande.setNombre(dto.getNombre());
        demande.setNiveau(dto.getNiveau());
        demande.setDateDembaucheSouhaitee(dto.getDateDembaucheSouhaitee());
        demande.setType(dto.getType());
        demande.setAge(dto.getAge());
        demande.setMotif(dto.getMotif());
        demande.setHoraireDeTravail(dto.getHoraireDeTravail());
        demande.setSexe(dto.getSexe());
        demande.setEtat(dto.getEtat());
        operation.setId(dto.getIdOperation());
        demande.setPosteAPouvoir(operation);
        demande.setMatriculeDemande("DR" + String.valueOf(Instant.now().toEpochMilli()).substring(10));
        Formations formation = new Formations();
        if(dto.getEtat()== EtatDemandeDeFormation.VALIDER_PAR_DIRECTEUR_OPERATIONS){
        formation.setId(dto.getIdFormations());
        demande.setFormation(formation);
        }
        return demande;
    }
}
