package model.problems;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CONSTRAINTS")
@Data
public class Constraint {
	@Id
	private Long id;
	
	private String constraint;
}
