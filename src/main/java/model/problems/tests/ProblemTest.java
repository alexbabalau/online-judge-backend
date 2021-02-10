package model.problems.tests;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import model.submissions.TestEvaluation;

@Entity
@Table(name = "TESTS")
@Data
public class ProblemTest {
	
	@OneToMany(mappedBy = "test")
	private List<TestEvaluation> evaluation;
	
}
