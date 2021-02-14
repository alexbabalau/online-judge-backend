package repository.problems;

import java.util.List;

import org.springframework.data.repository.Repository;

import model.problems.Problem;

public interface ProblemsRepository extends Repository<Problem, Long> {
	
	Problem save(Problem problem);
	
	Problem findById(Long id);
	
	List<Problem> findAll();
	
}
