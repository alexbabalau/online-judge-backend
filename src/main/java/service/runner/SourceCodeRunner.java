package service.runner;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.springframework.stereotype.Service;

@Service
public class SourceCodeRunner {
	
	public String run(File executable, String input) throws IOException, InterruptedException {
		
		Process process = new ProcessBuilder(executable.getAbsolutePath()).start();
		
		OutputStream processInput = process.getOutputStream();
		
		PrintWriter writer = new PrintWriter(processInput);
		
		writer.print(input);
		
		writer.close();
		
		InputStreamReader processOutput = new InputStreamReader(process.getInputStream());
		
		StringBuilder output = new StringBuilder("");
		char currentChar = 0;
		int auxChar;
		while((auxChar = processOutput.read()) != -1) {
			currentChar = (char)auxChar;
			output.append(currentChar);
		}
		processOutput.close();
		int exit = process.waitFor();
		if(exit != 0)
			return "";
		return output.toString();
	}
	
}
