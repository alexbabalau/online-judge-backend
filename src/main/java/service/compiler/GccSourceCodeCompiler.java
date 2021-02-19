package service.compiler;

import java.io.File;
import java.io.IOException;

public class GccSourceCodeCompiler implements SourceCodeCompiler {

	private File executable;
	private File sourceFile;
	
	public GccSourceCodeCompiler(File sourceFile) {
		this.sourceFile = sourceFile;
	}
	
	private void runGccProcess() throws IOException, InterruptedException{
		ProcessBuilder gccProcessBuilder = new ProcessBuilder("gcc", "-x", "c", "-o" + sourceFile.getAbsolutePath() + ".out", sourceFile.getAbsolutePath());
		Process gccProcess = gccProcessBuilder.start();
		int exitValue = gccProcess.waitFor();
		if(exitValue != 0) {
			throw new CompilerErrorException();
		}
		executable = new File(sourceFile.getAbsolutePath() + ".out");
	}
	
	@Override
	public void compileSourceFile(){
		
		try {
			runGccProcess();
		}
		
		catch(IOException | InterruptedException ex) {
			ex.printStackTrace();
			throw new CompilerErrorException();
		}
		finally {
			sourceFile.delete();
		}
		
	}

	@Override
	public File getExecutable() {
		return executable;
	}

}
