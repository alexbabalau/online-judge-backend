package repository.submissions;

import org.springframework.data.repository.Repository;

import model.submissions.TestEvaluation;
import model.submissions.TestEvaluationId;

public interface TestEvaluationRepository extends Repository<TestEvaluation, TestEvaluationId> {
	TestEvaluation save(TestEvaluation testEvaluation);
}
