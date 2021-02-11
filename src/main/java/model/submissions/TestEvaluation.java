package model.submissions;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.problems.tests.ProblemTest;

@Entity
@Table(name = "TEST_EVALUATION")
@NoArgsConstructor
@Data
public class TestEvaluation {
	
	@EmbeddedId
	private TestEvaluationId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("testId")
	@JoinColumn(name = "test_id")
	private ProblemTest test;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("submissionId")
	@JoinColumn(name = "submission_id")
	private Submission submission;
	
	private String status;
	
	private Integer score;
}
