package service.runner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.submissions.SourceCode;
import model.submissions.Submission;
import repository.StubProblemRepository;
import repository.StubSubmissionFactory;
import repository.StubTestEvaluationRepository;
import repository.problems.ProblemsRepository;
import service.compiler.SourceCodeCompilerFactory;
import service.compiler.SourceFileCreator;

public class SourceCodeEvaluatorTest {
	
	private static SourceCodeEvaluator evaluator;
	private static StubSubmissionFactory submissionFactory;
	
	@BeforeAll
	public static void setup() {
		evaluator = new SourceCodeEvaluator(new SourceFileCreator(), new SourceCodeCompilerFactory(), new SourceCodeRunner());
		submissionFactory = new StubSubmissionFactory();
	}
	
	@Test
	public void submissionTest(){
		Submission submission = submissionFactory.getSampleSubmission();
		try {
			evaluator.evaluate(submission);
		} catch (IOException | InterruptedException e) {
			fail("Unexpected Exception generated");
		}
		assertNotNull(submission.getScore());
		assertNotNull(submission.getStatus());
		assertEquals(Integer.valueOf(100), submission.getScore());
		assertEquals("Completed", submission.getStatus());
	}
}
