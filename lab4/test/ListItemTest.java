import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

public class ListItemTest {

    @org.junit.jupiter.api.Test
    public void writeHTML() {
        String content = "some text";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new ListItem().setContent(content).writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assertTrue(result.contains("<li>"));
        assertTrue(result.contains("</li>"));
        assertTrue(result.contains(content));
    }
}