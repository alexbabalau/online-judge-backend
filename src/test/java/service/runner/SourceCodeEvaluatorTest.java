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
import repository.StubTestEvaluationRepository;
import repository.problems.ProblemsRepository;
import service.compiler.SourceCodeCompilerFactory;
import service.compiler.SourceFileCreator;

public class SourceCodeEvaluatorTest {
	
	private static SourceCodeEvaluator evaluator;
	private static ProblemsRepository problemsRepository;
	
	@BeforeAll
	public static void setup() {
		evaluator = new SourceCodeEvaluator(new SourceFileCreator(), new SourceCodeCompilerFactory(), new StubTestEvaluationRepository(), new SourceCodeRunner());
		problemsRepository = new StubProblemRepository();
	}
	
	@Test
	public void submissionTest(){
		String code = "#include<stdio.h>\n"
				+ "int main(){\n"
				+ "int a,b;\n"
				+ "scanf(\"%d%d\", &a, &b);\n"
				+ "printf(\"%d\\n\",a + b);\n"
				+ "return 0;\n"
				+ "}"; 
		Submission submission = new Submission(null, null, null, null, null, "gcc", null, new SourceCode(null, null, code), null, problemsRepository.findById(Long.valueOf(0)));
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
