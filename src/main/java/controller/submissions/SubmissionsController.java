package controller.submissions;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import model.submissions.Submission;
import service.submissions.SubmissionsService;

@RestController
public class SubmissionsController {
	
	private SubmissionsService submissionsService;
	private final Integer SUBMISSIONS_PER_PAGE = 10;
	
	@Autowired
	public SubmissionsController(SubmissionsService submissionsService) {
		this.submissionsService = submissionsService;
	}
	
	@PostMapping("/submissions/{problemId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Submission addSubmission(@PathVariable Long problemId, @RequestBody Submission submission) throws IOException, InterruptedException {
		
		this.submissionsService.addSubmission(submission, problemId);
		Submission result = this.submissionsService.evaluateLastSubmission();
		//System.out.println(result);
		return result;
		
	}
	
	@GetMapping("/submissions/{problemId}/{page}")
	public List<Submission> getSubmissionsByProblemAndPage(@PathVariable Long problemId, @PathVariable Integer page){
		Pageable pageRequest = PageRequest.of(page, SUBMISSIONS_PER_PAGE, Sort.by("id"));
		return this.submissionsService.getPageOfSubmissionsByProblemId(problemId, pageRequest);
	}
	
}
