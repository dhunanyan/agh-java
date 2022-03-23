import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args){
        Document cv = new Document("Davit Hunanyan - CV");
        cv.setPhoto("https://st2.depositphotos.com/2783505/8415/i/600/depositphotos_84158918-stock-photo-passport-picture-of-a-hispanic.jpg");
        cv.addSection("Profil")
                .addParagraph("Silny lider ceniący uczciwość, metodyczne podejście do pracy i pracę grupową, zawsze chętny " +
                        "do podjęcia nowych wyzwań. Otwarta na zmiany osoba, nieustannie rozwijając siebie i innych, z pasją do " +
                        "przedsiębiorczości, programowania i obsługi klienta");
        cv.addSection("Profil")
                .addParagraph("10/2020 – obecnie Uniwersytet Pedagogiczny w Krakowie, Instytut Matematyki")
                .addParagraph("09/2016 - 05/2019 LO Zakonu Pijarow im. Stanisława Konarskiego w Krakowie " +
                        "Kierunek: Matematyka, Fizyka, Informatyka");
        cv.addSection("Ukończone kursy")
                .addParagraph("2019 Foundation Customer Experience Training (Training & Development Co.) / Szkolenie z podstawowej")
                .addParagraph("2019 Effective Time Management Training (Training & Development Co.) / Szkolenie z efektywnego zarządzania " +
                        "czasem");
        cv.addSection("Znajomość języków")
                .addParagraph(
                        new ParagraphWithList().setContent("")
                                .addItemToList("Ormiański – ojczysty")
                                .addItemToList("Angielski (C1)")
                                .addItemToList("Rosyjski (C1)")
                                .addItemToList("Polski (C1)")
                                .addItemToList("Francuski (A2)")
                                .addItemToList("Włoski (A2)"));
        cv.addSection("Umiejętności komputerowe")
                .addParagraph(
                        new ParagraphWithList().setContent("Software:")
                                .addItemToList("HTML")
                                .addItemToList("CSS")
                                .addItemToList("SCSS / SASS")
                                .addItemToList("Bootstrap")
                                .addItemToList("JavaScript / jQuery")
                                .addItemToList("React (Podstawy)")
                                .addItemToList("C++")
                                .addItemToList("Java (Podstawy)")
                                .addItemToList("Python (Podstawy)"))
                .addParagraph(
                new ParagraphWithList().setContent("Inne Programy:")
                        .addItemToList("Adobe Ps")
                        .addItemToList(" Figma")
                        .addItemToList("MS Office"));
        cv.addSection("Inne umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("")
                                .addItemToList("Wrażliwe i taktowne podejście do pracy z ludźmi")
                                .addItemToList(" Umiejętność jasnego, zwięzłego i skutecznego komunikowania własnych pomysłów w sposób jasny, zarówno\n" +
                                        "werbalnie, jak i w formie pisemnej")
                                .addItemToList(" Zdolność do wykazania się silnym przywództwem, wpływania i przekonywania umiejętności ")
                                .addItemToList("Silne i dobrze wyrażone umiejętności słuchania ")
                                .addItemToList("Doskonałe umiejętności organizacyjne ")
                                .addItemToList("Szeroka perspektywa, a także duża dbałość o szczegóły")
                                .addItemToList("Zdolność do pracy pod wpływem presji i stresu")
                                .addItemToList("Zdolność do podejmowania szybkich i skutecznych decyzji")
                                .addItemToList("Orientacja na wynik")
                                .addItemToList("Rzucanie wyzwania istniejącemu stanu rzeczy w celu jego poprawy\n")
                                .addItemToList("Nastawienie na zmiany"));
        cv.writeHTML(System.out);

        try {
            File file = new File("static\\cv.html");
            cv.writeHTML(new PrintStream(file,"UTF-8"));
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (UnsupportedEncodingException ex){
            System.out.println(ex);
        }

    }
}
