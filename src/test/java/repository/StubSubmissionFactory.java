package repository;

import model.submissions.SourceCode;
import model.submissions.Submission;

public class StubSubmissionFactory {
	
	private StubProblemRepository problemsRepository = new StubProblemRepository();
	
	public Submission getSampleSubmission() {
		String code = "#include<stdio.h>\n"
				+ "int main(){\n"
				+ "int a,b;\n"
				+ "scanf(\"%d%d\", &a, &b);\n"
				+ "printf(\"%d\\n\",a + b);\n"
				+ "return 0;\n"
				+ "}"; 
		Submission submission = new Submission(null, null, null, null, null, "gcc", null, new SourceCode(null, null, code), null, problemsRepository.findById(Long.valueOf(0)).orElse(null));
		return submission;
	}
	
}
