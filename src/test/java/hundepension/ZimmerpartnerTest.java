package hundepension;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZimmerpartnerTest {
    Hund h1 = new Hund("Bello","Pudel","Großer Hund", 5, "Rüde", true, "Hunden", "", true, "");
    String groesse = "Großer Hund";
    Zimmerpartner zimmerpartner = new Zimmerpartner(new Hundepension());

    @Test
    void groessefiltern_test() {
        assertEquals(true, zimmerpartner.groessefiltern(h1,groesse));
    }
}