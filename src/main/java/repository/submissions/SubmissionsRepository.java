package repository.submissions;

import org.springframework.data.repository.Repository;

import model.submissions.Submission;

public interface SubmissionsRepository extends Repository<Submission, Long>{
	
	Submission save(Submission submission);
	
	Submission findById(Long id);
	
}
