import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section{
    String title;
    List<Paragraph> paragraphs = new ArrayList<>();

    Section(String sectionTitle){
        this.title = sectionTitle;
    }

    Section setTitle(String title){
        this.title = title;
        return this;
    }

    Section addParagraph(String paragraphText){
        Paragraph p = new Paragraph().setContent(paragraphText);
        this.paragraphs.add(p);
        return this;
    }

    Section addParagraph(Paragraph p){
        this.paragraphs.add(p);
        return this;
    }

    void writeHTML(PrintStream out){
        out.println("<section>");
        out.println("<div class=\"container\">");
        out.println("<h2>");
        out.println(this.title);
        out.println("</h2>");
        for(Paragraph p : paragraphs ){
            p.writeHTML(out);
        }
        out.println("</div>");
        out.println("</section>");
    }
}
