package model.submissions;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import model.problems.tests.ProblemTest;

@Entity
@Table(name = "TEST_EVALUATION")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class TestEvaluation {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "test_id")
	@ToString.Exclude
	@JsonIgnore
	private ProblemTest test;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "submission_id")
	@JsonIgnore
	@ToString.Exclude
	private Submission submission;
	
	private String status;
	
	private Integer score;
}
