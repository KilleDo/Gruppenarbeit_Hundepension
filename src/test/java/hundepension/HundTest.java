package hundepension;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HundTest {
    Hund h1 = new Hund("Bello","Pudel","Großer Hund", 5, "Rüde", true, "Hunden", "", true, "");

    @Test
    void ausgeben_test() {
        assertEquals(" \n\nBello \n Rasse: Pudel\n Größe: Großer Hund\n Alter: 5.0\n Geschlecht: Rüde\n Kastriert: true\n Hat Probleme mit Hunden\n \n\n\n------------------------------"
                    ,h1.ausgeben());
    }
}