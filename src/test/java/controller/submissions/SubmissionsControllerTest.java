package controller.submissions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import application.OnlineJudgeApplication;
import model.problems.Problem;
import model.submissions.Submission;
import repository.StubProblemFactory;
import repository.StubSubmissionFactory;
import service.problems.ProblemsService;

@ExtendWith(SpringExtension.class)
public class SubmissionsControllerTest {
	
	private static StubSubmissionFactory submissionFactory;
	private static RestTemplate restTemplate = new RestTemplate();
	private static StubProblemFactory problemFactory;
	
	
	@BeforeAll
	public static void setup() {
		submissionFactory = new StubSubmissionFactory();
		problemFactory = new StubProblemFactory();
	}
	
	@Test
	
	public void addSubmissionTest() {
		Problem problem = restTemplate.postForObject("http://localhost:8080/problems", problemFactory.getSampleProblem(), Problem.class);
		
		assertNotNull(problem);
		
		Submission submission = submissionFactory.getSampleSubmission();
		submission.setProblem(null);
		submission = restTemplate.postForObject("http://localhost:8080/submissions/{problemId}", submission, Submission.class, problem.getId());
		
		assertNotNull(submission);
		
		assertEquals(Integer.valueOf(100), submission.getScore());
		assertEquals("Completed", submission.getStatus());
	}
	
}
