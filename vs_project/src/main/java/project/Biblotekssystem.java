package project;

public class Biblotekssystem {
    private static UsersList Usersregister;
    private static SuspendedUsersList SuspendedUsersregister;
    private static BooksList Booksregister;
    private static MagazinesList Magazinesregister;

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
                    Skriva ut (sorterat)
                    1. bok
                    2. tidning
                    3. kund
                """);
        String val = IO.readln();
        return val;
    }
}
