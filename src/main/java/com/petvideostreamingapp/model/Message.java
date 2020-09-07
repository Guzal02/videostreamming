package com.petvideostreamingapp.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table
@Data
@EqualsAndHashCode(of= {"id"})
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Id.class)
	private Long id;
	@JsonView(Views.IdName.class)
	private String text;

	@Column(updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonView(Views.FullMessage.class)
	private LocalDateTime creationDate;
}
