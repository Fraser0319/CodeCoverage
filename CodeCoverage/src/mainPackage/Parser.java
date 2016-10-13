package mainPackage;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class Parser {

	public static void main(String[] args) throws Exception {
		FileInputStream in = new FileInputStream("PracticalTwo.java");

		CompilationUnit cu;
		try {
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}
		//new MethodModVisitor().visit(cu,null);
		//new ModifierVisitor().visit(cu, null);
		//cu.accept(new ModifierVisitor(), null);
		// write the modified cu back...
		//System.out.println(cu.toString());

		// Write modified AST to a file
		byte[] modfile = cu.toString().getBytes();
		Path file = Paths.get("newFile.java");
		Files.write(file, modfile);

	}

}
