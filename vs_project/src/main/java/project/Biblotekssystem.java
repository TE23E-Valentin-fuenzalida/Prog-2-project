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
            1. Hämta alla böcker, tidning, kunder eller avstängda
            2. Skapa ny bok, tidning, kunder eller avstängd
            3. Hitta kund
            4. Hitta bok eller tidning
            5. Ta bort bok  eller tidning
            7. Ta bort kund eller avstängd
            8. Skriva ut sorterat böcker, kunder, 
            9. Avsluta
        """);
        String val = IO.readln();

        return val;
    }

    public void SuspendedUsers(){
        
    }
}
