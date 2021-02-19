package model.problems.tests;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "EXAMPLES")
public class Example extends BasicTest {

	public Example(Long id, String input, String output) {
		super(id, input, output);
	}
	
}
