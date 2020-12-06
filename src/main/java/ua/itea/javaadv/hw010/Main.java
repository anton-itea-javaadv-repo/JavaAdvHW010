package ua.itea.javaadv.hw010;

import org.xml.sax.SAXException;
import ua.itea.javaadv.hw010.catfarsh.BlackCat;
import ua.itea.javaadv.hw010.catfarsh.Blohable;
import ua.itea.javaadv.hw010.catfarsh.Cat;
import ua.itea.javaadv.hw010.catfarsh.CatYears;
import ua.itea.javaadv.hw010.catfarsh.Color;
import ua.itea.javaadv.hw010.catfarsh.FatCat;
import ua.itea.javaadv.hw010.catfarsh.ThinCat;
import ua.itea.javaadv.hw010.catfarsh.UglyCat;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

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

        System.out.println();
        System.out.println("=== Cat farsh ===");

        List<Class<? extends Cat>> cats = new ArrayList<>();
        cats.add(FatCat.class);
        cats.add(UglyCat.class);
        cats.add(ThinCat.class);
        cats.add(BlackCat.class);

        cats.forEach(c -> {
            Annotation[] annotations = c.getAnnotations();
            System.out.println("Current cat: " + c.getSimpleName());
            boolean farsheable = true;
            for (Annotation a : annotations) {
                if (a instanceof Blohable) {
                    System.out.println(c.getSimpleName() + " avoided being farsh because of Blohable");
                    farsheable = false;
                    break;
                } else if (a instanceof Color) {
                    Color colorAnno = (Color) a;
                    String catcolor = colorAnno.catcolor();
                    System.out.println("Cat color: " + catcolor);
                    if ("black".equals(catcolor)) {
                        double random = Math.random();
                        if (random < 0.5) {
                            System.out.println(c.getSimpleName() +
                                    " avoided being farsh because of it's black color, and cat's luck");
                            farsheable = false;
                            break;
                        } else {
                            System.out.println(c.getSimpleName() +
                                    " is black color, but it's no luck for him today");
                        }
                    }
                } else if (a instanceof CatYears) {
                    CatYears catYearsAnno = (CatYears) a;
                    int catAgeRes = catYearsAnno.age();
                    System.out.println("Cat age: " + catAgeRes);
                    if (catAgeRes > 2) {
                        System.out.println(c.getSimpleName() +
                                " avoided being farsh because of cat is too old");
                        farsheable = false;
                        break;
                    }
                }
            }
            if (farsheable) {
                System.out.println("Cat " + c.getSimpleName() + " has become a farsh!");
            }
            System.out.println();
        });
    }
}
