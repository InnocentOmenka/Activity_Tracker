package com.example.week8.models;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(length = 500)
	private String description;

	@Column(nullable = false)
	private String status;

	@ManyToOne()
	@JoinColumn(name = "user_id",
	referencedColumnName = "id"
	)
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created_At;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_At;

	@Temporal(TemporalType.TIMESTAMP)
	private Date completed_At;

	@PrePersist
	public void setCreatedAt(){
		created_At = new Date();
	}
	@PreUpdate
	public void setUpdatedAt(){
		updated_At= new Date();
	}

}
