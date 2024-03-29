package model.submissions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Embeddable
public class SourceCode {
	
	private Integer sizeInBytes;
	
	private String language;
	
	private String code;
}
