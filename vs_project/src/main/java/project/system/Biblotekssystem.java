package project.system;

import java.util.ArrayList;

import project.Basklasser.SuspendedUsers;
import project.Basklasser.Users;

/* 
Valentin fuenzalida ribbing
Biblotekssystemet innehåller en metod för huvud menyn och 6 andera menyer som under menyer
*/

import project.Listor.*;

//TODO create en stor meny metod för alla menyer
public class Biblotekssystem {


           // skapar alla object av klasserna som jag använder
       static UsersList listUsers = new UsersList();
       static MagazinesList listMagazines = new MagazinesList();
       static BooksList listBooks = new BooksList();
       static SuspendedUsersList listSuspendedUsers = new SuspendedUsersList();
       static Main main = new Main();

private ArrayList<MagazinesList> MagazinesReg;
private ArrayList<UsersList> UsersReg;
private ArrayList<BooksList> BooksReg;
private ArrayList<SuspendedUsersList> SuspendedUsersReg; 


    public void meny(){
        boolean program = true;
        IO.println("Startar JSON klient");
        IO.println();
        while (program) {

            String val1 = huvudMeny();

            switch (val1) {
                case "1":
                    String val2 = hämtaUtAllaMeny();
                    if (val2.equals("1")) {
                        listBooks.get_allBooks();
                    }
                    else if (val2.equals("2")) {
                        listMagazines.get_allMagazines();
                    } else if (val2.equals("3")) {
                        listUsers.get_allUsers();
                    } else if (val2.equals("4")) {
                        listSuspendedUsers.get_allSuspendedUsers();
                    }
                    break;
                case "2":
                    String val3 = hämtaUtEnMeny();
                    if (val3.equals("1")) {
                        listBooks.Sök();
                    }
                    else if (val3.equals("2")) {
                        listMagazines.Sök();
                    } else if (val3.equals("3")) {
                        listUsers.Sök();
                    } else if (val3.equals("4")) {
                        listSuspendedUsers.Sök();
                    }
                    break;
                case "3":
                    String val4 = SkapaNyttMeny();
                    if (val4.equals("1")) {
                        listBooks.LäggTill();
                    } else if (val4.equals("2")) {
                        listMagazines.LäggTill();
                    } else if (val4.equals("3")) {
                        listUsers.LäggTill();
                    } else if (val4.equals("4")) {
                        listSuspendedUsers.LäggTill();
                    }
                    break;
                case "4":
                    String val5 = SökaMeny();
                    if (val5.equals("1")) {
                        listBooks.Sök();
                    } else if (val5.equals("2")) {
                            listMagazines.Sök();
                    } else if (val5.equals("3")) {
                            listUsers.Sök();
                    }
                    break;
                case "5":
                    String val6 = TabortMeny();
                    if (val6.equals("1")) {
                        listBooks.TaBort();
                    }
                    else if (val6.equals("2")) {
                        listMagazines.TaBort();
                    } else if (val6.equals("3")) {
                        listUsers.TaBort();
                    } else if (val6.equals("4")) {
                        listSuspendedUsers.TaBort();
                    }
                    break;
                case "6":
                    String val7 = SkriaUtMeny();
                    if (val7.equals("1")) {
                        
                    } else if (val7.equals("2")) {
                        String val7_2 = Sortera();
                            if (val7_2.equals("1")) {
                                String val7_2_2 = SorteraBooks();
                                if (val7_2_2.equals("2")) {
                                    listBooks.SorteraGenre();
                                }
                            }
                    } else if (val7.equals("3")) {
                        String val7_3 = Filtrera();
                        if (val7_3.equals("1")) {
                            String val7_3_1 = FiltreraBooks();
                            if (val7_3_1.equals("2")) {
                                listBooks.filtreraFörfattare();
                            }
                        }
                    }
                    break;
                case "7":
                    fårduLåna();
                    break;
                default:
                    program = false;
                    break;
            }

        }
    }

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

    public void fårduLåna(){
        // hämtar alla Users och suspendedUsers från servern och lägger de i lista
        listUsers.get_allUsers();
        listSuspendedUsers.get_allSuspendedUsers();
        // frågar användaren för ett id för en kund
        IO.println("Skriv namnet på kunden: ");
        String namn = IO.readln().toLowerCase();
    
        //loopar igenom listUsers för att hitta ett object med samma namn
        for (Users user : listUsers.getListUsers()) {
            if (user.getName().toLowerCase().equals(namn)) {
                // hämtar id för den user som matchade namnet
                String id = user.getId();
                // loopar igenom suspendedUsers list för att kolla om någon av id matchar id för kunden
                for (SuspendedUsers suspendedUser : listSuspendedUsers.getListSuspendedUsers()) {
                    if (id.equals(suspendedUser.getUserId())) {
                        IO.println("Kunden är avstängd");
                        return;
                    }
                }
                IO.println("Kunden får låna");
                return;
            }
        }   
        IO.println("Ingen kund hittades");
    }
}
