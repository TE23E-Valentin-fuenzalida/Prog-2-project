package project;

public class Biblotekssystem {
    private static UsersList Usersregister;
    private static SuspendedUsersList SuspendedUsersregister;
    private static BooksList Booksregister;
    private static MagazinesList Magazinesregister;

    public String meny() {
        IO.println(
        """
        välj vad du vill göra
            1. Hämta böcker.
            2. Hämta tidningar.
            3. Skriva ut hämtade böcker eller tidningar på skärmen
            4. Lägg till bok.
            5. Lägg till tidning.
            6. Avsluta
        """);
        String val = IO.readln();

        return val;
    }

    
}
