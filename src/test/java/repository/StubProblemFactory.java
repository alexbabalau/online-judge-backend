package repository;

import java.util.ArrayList;
import java.util.List;

import model.problems.Problem;
import model.problems.tests.ProblemTest;

public class StubProblemFactory {

	public Problem getSampleProblem() {
		List<ProblemTest> tests = new ArrayList<ProblemTest>();
		for(int i = 0; i < 10; i++) {
			ProblemTest test = new ProblemTest(null, i + " " + i , 2 * i + "\n");
			tests.add(test);
		}
		Problem problem = new Problem(null, "", "", "", "", null, Integer.valueOf(1000), Integer.valueOf(1), null, null, tests, null, null);
		return problem;
	}
	
}
