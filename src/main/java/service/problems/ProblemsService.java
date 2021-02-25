package service.problems;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import model.problems.Problem;
import repository.problems.ProblemsRepository;

@Service
@Transactional
public class ProblemsService {
	
	private ProblemsRepository problemsRepository;
	
	@Autowired
	public ProblemsService(ProblemsRepository problemsRepository) {
		this.problemsRepository = problemsRepository;
	}
	
	public Problem addProblem(Problem problem) {
		return this.problemsRepository.save(problem);
	}
	
	public List<Problem> getAllProblems() {
		return this.problemsRepository.findAll();
	}
	
	public List<Problem> getAllProblemsByPage(Pageable pageable){
		return this.problemsRepository.findAll(pageable).toList();
	}
	
	public Problem getById(Long id) {
		return this.problemsRepository.findById(id).orElse(null);
	}
	
	public Problem updateProblem(Long id, Problem newProblem) {
		newProblem.setId(id);
		return this.problemsRepository.save(newProblem);
	}
}
