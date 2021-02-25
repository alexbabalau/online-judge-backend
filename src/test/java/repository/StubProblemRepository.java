package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
	public Optional<Problem> findById(Long id) {
		return Optional.of(problems.get(id.intValue()));
	}

	@Override
	public List<Problem> findAll() {
		return problems;
	}

	@Override
	public Iterable<Problem> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Problem> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Problem> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Problem> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Problem entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Problem> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	
	
}
