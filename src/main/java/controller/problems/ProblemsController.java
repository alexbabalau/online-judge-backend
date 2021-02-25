package controller.problems;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import model.problems.Problem;
import model.problems.tests.ProblemTest;
import service.problems.ProblemsService;

@RestController
public class ProblemsController {
	
	private ProblemsService problemsService;
	
	private final Integer PROBLEMS_PER_PAGE = 10;
	
	
	@Autowired
	public ProblemsController(ProblemsService problemsService) {
		this.problemsService = problemsService;
	}
	
	@PostMapping("/problems")
	@ResponseStatus(HttpStatus.CREATED)
	public Problem addProblem(@RequestBody Problem problem) {
		System.out.println(problem);
		for(ProblemTest test:problem.getTests()) {
			System.out.println(test.getId());
		}
		return this.problemsService.addProblem(problem);
	}
	
	@PutMapping("/problems/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProblem(@RequestBody Problem problem, @PathVariable("id") Long id) {
		this.problemsService.updateProblem(id, problem);
	}
	
	@GetMapping("/problems/{id}")
	public Problem getProblemById(@PathVariable Long id) {
		return this.problemsService.getById(id);
	}
	
	@GetMapping("/problems/page/{page}")
	public List<Problem> getProblemsByPage(@PathVariable Integer page){
		Pageable pageRequest = PageRequest.of(page - 1, PROBLEMS_PER_PAGE, Sort.by("id"));
		return problemsService.getAllProblemsByPage(pageRequest);
	}
	
}
