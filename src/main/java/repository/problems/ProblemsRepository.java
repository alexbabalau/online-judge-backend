package repository.problems;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import model.problems.Problem;

public interface ProblemsRepository extends PagingAndSortingRepository<Problem, Long> {
	
	Problem save(Problem problem);
	
	Optional<Problem> findById(Long id);
	
	List<Problem> findAll();
	
	Page<Problem> findAll(Pageable pageable);
	
}
