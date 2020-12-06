package ua.itea.javaadv.hw010;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp;

        SimplePomParser parser = new SimplePomParser();

        try {
            sp = spf.newSAXParser();
            sp.parse(Main.class.getResourceAsStream("/pom_hw6.xml"), parser);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        System.out.println(parser.getProject());
    }
}
