package model.problems;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CONSTRAINTS")
@Data
@NoArgsConstructor
public class Constraint {
	@Id
	private Long id;
	
	private String constraint;
}
