package com.pfe.Back.DTO;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.Back.model.ListProgramme;
import com.pfe.Back.model.SessionFormation;
import com.pfe.Back.model.programFormation;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SessionFormationDTO {

	private Integer id ;
	private String dateDebut ;
	private String dateFin ;
	private String theme ;
	private String type ;
	private String etat ;
	private Integer duree ;
	private String partie ;
	private String Resp ;
	private String Doc ;

	@JsonIgnore
    private programFormation program ;
	
	@JsonIgnore
	private List<ListProgramme> programme ;


	
	
	public static SessionFormationDTO fromEntity( SessionFormation session) {
    	if(session == null) {
    		return null ;
    	}
    	
    	return SessionFormationDTO.builder()
    			.id(session.getId())
    			.Resp(session.getResp())
    			.Doc(session.getDoc())
    			.dateDebut(session.getDateDebut())
    			.dateFin(session.getDateFin())
    			.theme(session.getTheme())
    			.type(session.getType())
    			.etat(session.getEtat())
    			.duree(session.getDuree())
    			.partie(session.getPartie())
    			//.programme(session.getProgramme())
    			.program(session.getProgram())
    			.build();
    }
  public static  SessionFormation toEntity(SessionFormationDTO sessionDTO)
  {
	  SessionFormation session = new  SessionFormation();
	  
	  session.setId(sessionDTO.getId());
	  session.setResp(sessionDTO.getResp());
	  session.setDoc(sessionDTO.getDoc());
	  session.setDateDebut(sessionDTO.getDateDebut());
	  session.setDateFin(sessionDTO.getDateFin());
	  session.setTheme(sessionDTO.getTheme());
	  //session.setProgramme(sessionDTO.getProgramme());
	  session.setType(sessionDTO.getType());
	  session.setEtat(sessionDTO.getEtat());
	  session.setDuree(sessionDTO.getDuree());
	  session.setPartie(sessionDTO.getPartie());
	  session.setProgram(sessionDTO.getProgram());



	  return session ;
  }



}
