import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> listItems = new ArrayList<>();

    UnorderedList addItem(ListItem li){
        this.listItems.add(li);
        return this;
    }

    void writeHTML(PrintStream out){
        out.println("<ul>");
        for(ListItem li: listItems){
            li.writeHTML(out);
        }
        out.println("</ul>");
    }
}
