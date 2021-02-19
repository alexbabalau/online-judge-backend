package service.submissions;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.submissions.Submission;
import repository.submissions.SubmissionsRepository;
import service.runner.SourceCodeEvaluator;

@Service
public class SubmissionsService {
	
	private SourceCodeEvaluator evaluator;
	private SubmissionsRepository submissionRepository;
	private Long lastAddedSubmissionId;
	
	@Autowired
	public SubmissionsService(SourceCodeEvaluator evaluator) {
		this.evaluator = evaluator;
	}
	
	@Transactional
	public void addSubmission(Submission submission){
		submission.setStatus("Pending");
		submission = submissionRepository.save(submission);
		
	}
	
	@Transactional(rollbackFor = {IOException.class, InterruptedException.class})
	public void evaluateLastSubmission() throws IOException, InterruptedException{
		Submission submission = submissionRepository.findById(lastAddedSubmissionId);
		evaluator.evaluate(submission);
		submissionRepository.save(submission);
	}
}
