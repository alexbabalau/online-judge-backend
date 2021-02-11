package model.problems;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.problems.tests.ProblemTest;
import model.problems.tests.Example;
import model.submissions.Submission;

@Entity
@Table(name="PROBLEMS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Problem {
	
	@Id
	private Long id;
	
	private String title;
	
	private String description;
	
	private String inputFormat;
	
	private String outputFormat;
	
	private String exampleExplanations;
	
	@Column(name = "TIME_LIMIT")
	private Integer timeLimitInMiliseconds;
	
	@Column(name = "MEMORY_LIMIT")
	private Integer memoryLimitInMegaBytes;
	
	private String tutorial;
	
	@OneToMany
	@JoinColumn(name = "PROBLEM_ID")
	private List<Example> examples;
	
	@OneToMany
	@JoinColumn(name = "PROBLEM_ID")
	private List<ProblemTest> tests;
	
	@OneToMany
	@JoinColumn(name = "PROBLEM_ID")
	private List<Constraint> constraints;
	
	@OneToMany
	@JoinColumn(name = "PROBLEM_ID")
	private List<Submission> submissions;
}
