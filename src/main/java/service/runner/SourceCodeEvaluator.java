package service.runner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.problems.Problem;
import model.problems.tests.ProblemTest;
import model.submissions.Submission;
import model.submissions.TestEvaluation;
import repository.submissions.TestEvaluationRepository;
import service.compiler.SourceCodeCompiler;
import service.compiler.SourceCodeCompilerFactory;
import service.compiler.SourceFileCreator;

@Service
public class SourceCodeEvaluator {
	
	private SourceFileCreator fileCreator;
	private SourceCodeCompilerFactory compilerFactory;
	private SourceCodeRunner runner;
	
	private List<TestEvaluation> evaluationReport;
	
	@Autowired
	public SourceCodeEvaluator(SourceFileCreator fileCreator, SourceCodeCompilerFactory compilerFactory, SourceCodeRunner runner) {
		this.fileCreator = fileCreator;
		this.compilerFactory = compilerFactory;
		this.runner = runner;
	}
	
	private File getExecutableForEvaluation(String code, String compilerName) throws IOException {
		File sourceFile = fileCreator.createSourceFileWithCode(code);
		SourceCodeCompiler compiler = compilerFactory.createCompilerForSourceFile(compilerName, sourceFile);
		compiler.compileSourceFile();
		return compiler.getExecutable();
	}
	
	@Transactional
	public void evaluate(Submission submission) throws IOException, InterruptedException {
		File executable = getExecutableForEvaluation(submission.getCode().getCode(), submission.getCompiler());
		evaluationReport = getTestEvaluations(executable, submission);
		submission.setStatus("Completed");
		submission.setScore(getScoreEvaluationReport());
		executable.delete();
	}
	
	public List<TestEvaluation> getEvaluationReport(){
		return evaluationReport;
	}
	
	private Integer getScoreEvaluationReport() {
		return evaluationReport.stream().mapToInt(evaluation -> evaluation.getScore()).sum();
	}
	
	private List<TestEvaluation> getTestEvaluations(File executable, Submission submission) throws IOException, InterruptedException {
		Problem problem = submission.getProblem();
		List<ProblemTest> tests = problem.getTests();
		List<TestEvaluation> result = new ArrayList<TestEvaluation>();
		Integer score = 0;
		for(ProblemTest test : tests) {
			TestEvaluation evaluation = getEvaluation(executable, test);
			evaluation.setSubmission(submission);
			evaluation.setTest(test);
			result.add(evaluation);
		}
		return result;
	}
	
	private TestEvaluation getEvaluation(File executable, ProblemTest test) throws IOException, InterruptedException {
		TestEvaluation evaluation = new TestEvaluation();
		String output = runner.run(executable, test.getInput()).stripTrailing();
		if(output.equals(test.getOutput().stripTrailing())) {
			evaluation.setStatus("Correct");
			evaluation.setScore(10);
		}
		else {
			evaluation.setStatus("Wrong");
			evaluation.setScore(0);
		}
		return evaluation;
	}
	
}
