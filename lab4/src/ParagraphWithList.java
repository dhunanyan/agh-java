import java.io.PrintStream;

public class ParagraphWithList extends Paragraph{
    UnorderedList unorderedList = new UnorderedList();

    ParagraphWithList setContent(String textContent){
        this.content = textContent;
        return this;
    }

    ParagraphWithList addItemToList(String text){
        ListItem item = new ListItem();
        item.setContent(text);
        this.unorderedList.addItem(item);
        return this;
    }

    void writeHTML(PrintStream out) {
        out.println("<p>");
            if(this.content != null){
                out.printf(this.content);
            }
            unorderedList.writeHTML(out);
        out.println("</p>");
    }
}
