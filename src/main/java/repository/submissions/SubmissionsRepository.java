package repository.submissions;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import model.submissions.Submission;

public interface SubmissionsRepository extends Repository<Submission, Long>{
	
	Submission save(Submission submission);
	
	Submission findById(Long id);
	
	List<Submission> findByProblem_Id(Long id, Pageable pageable);
}
