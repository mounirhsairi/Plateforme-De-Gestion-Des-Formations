package com.pfe.Back.model;

import java.io.Serializable; 
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id ;
	 @CreatedDate
	 @Column(name = "creationDate" , nullable = false ,updatable= false)
	 private String creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	 @LastModifiedDate
	 @Column(name = "lastModifiedDate")
	 private String lastUpdateDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));  
	@LastModifiedBy
	 @Column(name = "last_modified_by")
	 private String lastModifiedBy ;

	 
}
