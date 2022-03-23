import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphWithListTest {
    @org.junit.jupiter.api.Test
    public void writeHTML() {
        String content = "some text or unordered list";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new ParagraphWithList().setContent(content).writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assertTrue(result.contains("<p>"));
        assertTrue(result.contains("<ul>"));
        assertTrue(result.contains("</ul>"));
        assertTrue(result.contains("</p>"));
        assertTrue(result.contains(content));
    }
}