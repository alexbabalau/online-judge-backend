package service.submissions;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.submissions.Submission;
import model.submissions.TestEvaluation;
import repository.submissions.SubmissionsRepository;
import repository.submissions.TestEvaluationRepository;
import service.problems.ProblemsService;
import service.runner.SourceCodeEvaluator;

@Service
public class SubmissionsService {
	
	private SourceCodeEvaluator evaluator;
	private SubmissionsRepository submissionRepository;
	private Long lastAddedSubmissionId;
	private ProblemsService problemsService;
	private TestEvaluationRepository testEvaluationRepository;
	
	@Autowired
	public SubmissionsService(SourceCodeEvaluator evaluator,
			SubmissionsRepository submissionRepository,
			ProblemsService problemsService,
			TestEvaluationRepository testEvaluationRepository) {
		this.evaluator = evaluator;
		this.submissionRepository = submissionRepository;
		this.problemsService = problemsService;
		this.testEvaluationRepository = testEvaluationRepository;
	}
	
	
	
	@Transactional
	public void addSubmission(Submission submission, Long problemId){
		submission.setProblem(problemsService.getById(problemId));
		submission.setStatus("Pending");

		submission = submissionRepository.save(submission);
		lastAddedSubmissionId = submission.getId();
	}
	
	private void saveEvaluationReport(List<TestEvaluation> report) {
		for(TestEvaluation testEvaluation : report) {
			testEvaluationRepository.save(testEvaluation);
		}
	}
	
	@Transactional(rollbackFor = {IOException.class, InterruptedException.class})
	public Submission evaluateLastSubmission() throws IOException, InterruptedException{
		Submission submission = submissionRepository.findById(lastAddedSubmissionId);
		evaluator.evaluate(submission);
		List<TestEvaluation> report = evaluator.getEvaluationReport();
		saveEvaluationReport(report);
		return submissionRepository.save(submission);
	}
	
	@Transactional
	public List<Submission> getPageOfSubmissionsByProblemId(Long problemId, Pageable pageable){
		return this.submissionRepository.findByProblem_Id(problemId, pageable);
	}
}
