package model.submissions;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestEvaluationId implements Serializable{
	
	@Column(name = "test_id")
	@GeneratedValue
	private Long testId;
	
	@Column(name = "submission_id")
	@GeneratedValue
	private Long submissionId;
	
}
