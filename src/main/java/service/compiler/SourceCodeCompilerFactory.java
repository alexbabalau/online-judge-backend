package service.compiler;

import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class SourceCodeCompilerFactory {
	
	public SourceCodeCompiler createCompilerForSourceFile(String compiler, File sourceFile) {
		switch(compiler) {
			case "gcc": return new GccSourceCodeCompiler(sourceFile);
		}
		throw new CompilerNotSupportedException();
	}
}
