package com.pfe.Back.DTO;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.pfe.Back.model.DemandeDePolyvalence;
import com.pfe.Back.model.DemandeDePolyvalenceData;
import com.pfe.Back.model.EtatDemandeDeFormation;
import com.pfe.Back.model.Formations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeDePolyvalenceDTO {
    private Integer id ;
    private List<DemandeDePolyvalenceDataDTO> demandDataList;
	private String MatriculeDemande ;
    private Integer idFormations;
	private EtatDemandeDeFormation etat ;
	private String lastModifiedBy ;
	private String creationDate ;
	private String dateFin;
	private String dateDebut;


    public static DemandeDePolyvalenceDTO fromEntity(DemandeDePolyvalence demande) {
        if (demande == null) {
            return null;
        }
        return DemandeDePolyvalenceDTO.builder()
        		.id(demande.getId())
        		/*.dateDebut(demande.getDateDebut())
        		.dateFin(demande.getDateFin())*/
        		.MatriculeDemande(demande.getMatriculeDemande())
                .idFormations(demande.getFormationPoly() != null ? demande.getFormationPoly().getId() : null)
                .demandDataList(demande.getDemandDataList().stream().map(DemandeDePolyvalenceDataDTO::fromEntity).collect(Collectors.toList()))
                .etat(demande.getEtat())
                .lastModifiedBy(demande.getLastModifiedBy())
                .creationDate(demande.getCreationDate())
                .build();
    }

    public static DemandeDePolyvalence toEntity(DemandeDePolyvalenceDTO dto) {
        if (dto == null) {
            return null;
        }
        DemandeDePolyvalence demande = new DemandeDePolyvalence();
        demande.setId(dto.getId());
        Formations formation = new Formations();
        if(dto.getEtat()== EtatDemandeDeFormation.VALIDER_PAR_RESPONSABLE_UAP){
            formation.setId(dto.getIdFormations());
            demande.setFormationPoly(formation);
            }
        demande.setEtat(dto.getEtat());
        /*demande.setDateDebut(dto.getDateDebut());
        demande.setDateFin(dto.getDateFin());*/
        demande.setLastModifiedBy(dto.getLastModifiedBy());
        demande.setMatriculeDemande("DP" + String.valueOf(Instant.now().toEpochMilli()).substring(10));
        demande.setDemandDataList(dto.getDemandDataList().stream().map(DemandeDePolyvalenceDataDTO::toEntity).collect(Collectors.toList()));
        return demande;
    }
}
