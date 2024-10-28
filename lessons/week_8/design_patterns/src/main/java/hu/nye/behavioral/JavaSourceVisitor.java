package hu.nye.behavioral;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class JavaSourceVisitor extends VoidVisitorAdapter<Void> {
    private final JavaParser parser = new JavaParser();

    @SneakyThrows
    public CompilationUnit getMySource() {
        String tmp = "src/main/java/" + this.getClass().getName().replace('.', '/') + ".java";
        tmp = FileUtils.readFileToString(new File(tmp));
        return parser.parse(tmp).getResult().get();
    }

    @Override
    public void visit(MethodDeclaration md, Void arg) {
        super.visit(md, arg);
        System.out.println("Method Name: " + md.getName());
    }
}
