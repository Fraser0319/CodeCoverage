
import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SimpleUMLJPreln {

	public static void main(String[] args) throws Exception {
		FileInputStream in = new FileInputStream("PracticalTwo.java");

		CompilationUnit cu;
		try {
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}

		new ClassDiagramVisitor().visit(cu, null);
	}

	/**
	 * Simple visitor implementation for extracting class information
	 */
	private static class ClassDiagramVisitor extends VoidVisitorAdapter {

		public void visit(FieldDeclaration n, Object a) {
			System.out.println("Field Type is: " + n.getElementType());
			for (VariableDeclarator v : n.getVariables()) {
				System.out.println("Name: " + v.getId().getName());
			}
		}
		


		public void visit(VariableDeclarationExpr n, Object a) {
			System.out.println("Var Type is: " + n.getElementType());
			for (VariableDeclarator v : n.getVariables()) {
				System.out.println("Name: " + v.getId().getName() + " line num: " + v.getBegin().line);
			}
		}

		 public void visit(VariableDeclarator v, Object a){
		 System.out.println("Name: " + v.getId().getName());
		 }

	}
}
