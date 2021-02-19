package service.compiler;

import java.io.File;

public interface SourceCodeCompiler {
	
	void compileSourceFile();
	
	File getExecutable();
}
