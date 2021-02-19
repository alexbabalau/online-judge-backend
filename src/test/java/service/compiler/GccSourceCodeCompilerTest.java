package service.compiler;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GccSourceCodeCompilerTest {
	
	private static File sourceFile;
	private static GccSourceCodeCompiler compiler;
	
	@BeforeAll
	public static void setup() throws IOException {
		sourceFile = new File("test.c");
		sourceFile.createNewFile();
		System.out.println(sourceFile.getAbsolutePath());
		String code = "#include<stdio.h>\n"
				+ "int main(){\n"
				+ "printf(\"Hello World\\n\");\n"
				+ "return 0;\n"
				+ "}";
		BufferedWriter writer = new BufferedWriter(new FileWriter(sourceFile));
	    writer.write(code);
	    writer.close();
	    compiler = new GccSourceCodeCompiler(sourceFile);
	}
	
	@Test
	public void compileTest() {
		
		try{
			compiler.compileSourceFile();
		}
		catch(CompilerErrorException ex) {
			fail("Should not have compilation errors");
		}
		catch(Exception ex) {
			fail("Unexpected exception");
		}
		
	}
	
}
