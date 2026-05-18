package project.system;

/* 
Valentin fuenzalida ribbing
Biblotekssystemet innehåller en metod för huvud menyn och 6 andera menyer som under menyer
*/

//TODO create en stor meny metod för alla menyer
public class Biblotekssystem {

    // huvudmeny
    public String huvudMeny() {
        IO.println("""
                Välj vad du vill göra
                    1. Hämta ut (alla)
                    2. Hämta ut (en)
                    3. Skapa nytt
                    4. Söka
                    5. Ta bort
                    6. Skriva ut (sorterat)
                    7. Låna
                    8. Avsluta
                        """);
        String val = IO.readln();

        return val;
    }

    public String hämtaUtAllaMeny() {
        IO.println("""
                    Hämta ut (alla)
                    1. Böcker
                    2. tidningar
                    3. kunder
                    4. avstängda
                """);
        String val = IO.readln();

        return val;
    }

    public String hämtaUtEnMeny() {
        IO.println("""
                    Hämta ut (en)
                    1. bok
                    2. tidning
                    3. kund
                    4. avstängd
                """);
        String val = IO.readln();
        return val;
    }

    public String SkapaNyttMeny() {
        IO.println("""
                    Skapa nytt
                    1. Ny bok
                    2. Ny tidning
                    3. Ny kund
                    4. Ny avstängd
                """);
        String val = IO.readln();
        return val;
    }

    public String SökaMeny() {
        IO.println("""
                    Söka
                    1. bok
                    2. tidning
                    3. kund
                """);
        String val = IO.readln();
        return val;
    }

    public String TabortMeny() {
        IO.println("""
                    Ta bort
                    1. bok
                    2. tidning
                    3. kund
                    4. avstängd
                """);
        String val = IO.readln();
        return val;
    }

    public String SkriaUtMeny() {
        IO.println("""
                välj hur du vill skriva ut
                1. Bokstavsordning
                2. Sortera
                3. Filtrerar
                4. Antal
                """);
        String val = IO.readln();
        return val;
    }

    // Filtrera
    public String Filtrera() {
        IO.println("""
                Filtrera
                1. Böcker
                2. Tidningar
                """);
        String val = IO.readln();
        return val;
    }

    // Filtrera Böcker
    public String FiltreraBooks() {
        IO.println("""
                1. genre
                2. Författare
                        """);
        String val = IO.readln();
        return val;
    }

    // filtrera tidningar
    public String FiltreraMagazines() {
        IO.println("""
                1. Published Year
                2. Category
                        """);
        String val = IO.readln();
        return val;
    }

    // sortera
    public String Sortera() {
        IO.println("""
                Sortera
                1. Böcker
                2. Tidningar
                        """);
        String val = IO.readln();
        return val;
    }

    // sortera böcker
    public String SorteraBooks() {
        IO.println("""
                1. Författare
                2. genre
                3. isAvaliable
                        """);
        String val = IO.readln();
        return val;
    }

    // sortera tidningar
    public String SorteraMagazines() {
        IO.println("""
                1. Published Year
                2. genre
                3. isAvaliable
                        """);
        String val = IO.readln();
        return val;
    }

    // Antal
    public String Antal() {
        IO.println("""
                Antal
                1. Böcker
                2. Tidningar
                3. Kunder
                4. Avstängda
                        """);
        String val = IO.readln();
        return val;
    }

    // Antal Böcker
    public String AntalBooks() {
        IO.println("""
                1. antal Böcker för en specifik författare
                2. antal Böcker för en specifik genre
                3. antal Böcker som kan lånas
                        """);
        String val = IO.readln();
        return val;
    }

    // Antal Tidningar
    public String AntalMagazines() {
        IO.println("""
                1. antal tidningar på ett specifikt år
                2. antal tidningar på en specifk kategori
                3. antal tidningar som kan lånas
                        """);
        String val = IO.readln();
        return val;
    }
}
