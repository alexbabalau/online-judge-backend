package model.submissions;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.problems.Problem;

@Entity
@Table(name = "SUBMISSIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {
	
	@Id
	private Long id;
	
	@Column(name = "MAX_TIME")
	private Integer timeInMiliseconds;
	
	@Column(name = "MAX_MEMORY")
	private Integer memoryInMegaBytes;
	
	private Date date;
	
	private Integer score;
	
	private String compiler;
	
	private String status;
	
	@Embedded
	@AttributeOverride(name = "sizeInBytes", column = @Column(name = "SIZE"))
	private SourceCode code;
	
	@OneToMany(mappedBy = "submission")
	private List<TestEvaluation> evaluations;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_id")
	private Problem problem;
	
}
