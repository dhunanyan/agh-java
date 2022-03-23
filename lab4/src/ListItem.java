import java.io.PrintStream;

public class ListItem {
    String content;

    ListItem setContent(String li){
        this.content = li;
        return this;
    }

    void writeHTML(PrintStream out){
        out.println("<li>");
        out.println(this.content);
        out.println("</li>");
    }
}
