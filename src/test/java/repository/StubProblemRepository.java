package repository;

import java.util.ArrayList;
import java.util.List;

import model.problems.Problem;
import model.problems.tests.ProblemTest;
import repository.problems.ProblemsRepository;

public class StubProblemRepository implements ProblemsRepository{

	private List<Problem> problems;
	
	public StubProblemRepository() {
		List<ProblemTest> tests = new ArrayList<ProblemTest>();
		for(int i = 0; i < 10; i++) {
			ProblemTest test = new ProblemTest(Long.valueOf(i), i + " " + i , 2 * i + "\n");
			tests.add(test);
		}
		Problem problem = new Problem(Long.valueOf(0), "", "", "", "", null, Integer.valueOf(1000), Integer.valueOf(1), null, null, tests, null, null);
		problems = new ArrayList<Problem>();
		problems.add(problem);
	}
	
	@Override
	public Problem save(Problem problem) {
		if(problem.getId() == null) {
			problem.setId(Long.valueOf(problems.size()));
			problems.add(problem);
		}
		else {
			problems.set(problem.getId().intValue(), problem);
		}
		return problem;
	}

	@Override
	public Problem findById(Long id) {
		return problems.get(id.intValue());
	}

	@Override
	public List<Problem> findAll() {
		return problems;
	}
	
	
}
