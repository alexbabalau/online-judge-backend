package model.submissions;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "SUBMISSIONS")
@Data
public class Submission {
	
	@Id
	private Long id;
	
	@Column(name = "MAX_TIME")
	private Integer timeInMiliseconds;
	
	@Column(name = "MAX_MEMORY")
	private Integer memoryInMegaBytes;
	
	private Date date;
	
	private Integer score;
	
	@Embedded
	@AttributeOverride(name = "sizeInBytes", column = @Column(name = "SIZE"))
	private SourceCode code;
	
	@OneToMany(mappedBy = "submission")
	private List<TestEvaluation> evaluations;
	
}
