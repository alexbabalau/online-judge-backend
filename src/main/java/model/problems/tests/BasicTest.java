package model.problems.tests;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public class BasicTest {
	@Id
	@GeneratedValue
	private Long id;
	
	private String input;
	
	private String output;
}
