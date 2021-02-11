package model.problems.tests;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.submissions.TestEvaluation;

@Entity
@Table(name = "TESTS")
@Data
@NoArgsConstructor
public class ProblemTest extends BasicTest{
	
	@OneToMany(mappedBy = "test")
	private List<TestEvaluation> evaluation;
	
}
