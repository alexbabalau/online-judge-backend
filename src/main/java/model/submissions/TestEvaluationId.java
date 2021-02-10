package model.submissions;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class TestEvaluationId implements Serializable{
	
	@Column(name = "test_id")
	private Long testId;
	
	@Column(name = "submission_id")
	private Long submissionId;
	
}
