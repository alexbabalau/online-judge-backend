package service.compiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class SourceFileCreator {
	
	private File createTempFile() throws IOException{
		File temp = File.createTempFile("online-judge-", ".tmp");
		temp.deleteOnExit();
		return temp;
	}
	
	private void writeCodeInFile(String code, File file) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    writer.write(code);
	    writer.close();
	}
	
	public File createSourceFileWithCode(String code) throws IOException{
		File temp = createTempFile();
		writeCodeInFile(code, temp);
		return temp;
	}
	
}
