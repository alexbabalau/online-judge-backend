package model.problems.tests;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import model.submissions.TestEvaluation;

@Entity
@Table(name = "TESTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemTest extends BasicTest{

	public ProblemTest(Long id, String input, String output) {
		super(id, input, output);
	}

	@OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<TestEvaluation> evaluation;
	
}
