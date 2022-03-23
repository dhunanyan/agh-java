import java.io.PrintStream;

public class Paragraph {
    String content;

    Paragraph setContent(String p){
        this.content = p;
        return this;
    }

    void writeHTML(PrintStream out){
        out.println("<p>");
        out.println(this.content);
        out.println("</p>");
    }
}
