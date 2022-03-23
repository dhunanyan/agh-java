import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document{
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document(String s){
        this.title = s;
    }

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        this.photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        Section s = new Section(sectionTitle);
        this.sections.add(s);
        return s;
    }

    Document addSection(Section s){
        this.sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out){
        out.println("<!DOCTYPE html>\n");
        out.println("<html lang=\"en\">\n");
        out.println("<head>\n");
        out.println("<meta charset=\"UTF-8\">\n");
        out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        out.println("<link rel=\"stylesheet\" href=\"css/normalize.css\">\n");
        out.println("<link rel=\"stylesheet\" href=\"css/reset.css\">\n");
        out.println("<link rel=\"stylesheet\" href=\"css/main.css\">\n");
        out.println("<title>CV</title>\n");
        out.println("</head>\n");
        out.println("<body>\n");
        out.println("<header class=\"header\">\n");
        out.println("<div class=\"header__container container\">\n");
        out.println("<div class=\"header__content\">\n");
        out.println("<h1 class=\"header__title\">\n");
        out.println(this.title);
        out.println("</h1>\n");
        out.println("<p class=\"header__descr\">\n");
        out.println("U L . W E S E L E 3 2 / 4 , 3 0 - 1 2 7 K R A K Ó W , P O L S K A\n" +
                "+ 4 8 5 0 7 2 3 7 5 7 8 • D A V I T . H U N A N Y A N @ M E . C O M\n");
        out.println("</p>\n");
        out.println("</div>\n");
        out.println("<div class=\"header__img\">\n");
        this.photo.writeHTML(out);
        out.println("</div>\n");
        out.println("</div>\n");
        out.println("</header>\n");
        out.println("<main>\n");

        for(Section s: sections){
            s.writeHTML(out);
        }

        out.println("</main>\n");
        out.println("</body>\n");
        out.println("</html>");
    }
}
