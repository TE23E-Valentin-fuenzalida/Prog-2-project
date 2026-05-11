package project;

/* 
Valentin fuenzalida ribbing
Main innehåller objekt av alla klass listor jag har och bioblotekssystemet.
Den innehåller också de olika valen jag kan göra.
Dessutom metoden för om en användare får låna eller inte låna
*/

public class Main {
    // baseURL för skolan
/*     public static final String baseURL = "http://10.151.168.5:3148/"; */
    // baseUrl för ej skolan
       public static final String baseURL = "http://localhost:3000/";
       // skapar alla object av klasserna som jag använder
       static Biblotekssystem system = new Biblotekssystem();
       static UsersList listUsers = new UsersList();
       static MagazinesList listMagazines = new MagazinesList();
       static BooksList listBooks = new BooksList();
       static SuspendedUsersList listSuspendedUsers = new SuspendedUsersList();
    void main() {
        boolean program = true;
        IO.println("Startar JSON klient");
        IO.println();
        while (program) {

            String val1 = system.huvudMeny();

            switch (val1) {
                case "1":
                    String val2 = system.hämtaUtAllaMeny();
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
                    String val3 = system.hämtaUtEnMeny();
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
                    String val4 = system.SkapaNyttMeny();
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
                    String val5 = system.SökaMeny();
                    if (val5.equals("1")) {
                        listBooks.Sök();
                    } else if (val5.equals("2")) {
                            listMagazines.Sök();
                    } else if (val5.equals("3")) {
                            listUsers.Sök();
                    }
                    break;
                case "5":
                    String val6 = system.TabortMeny();
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
                    String val7 = system.SkriaUtMeny();
                    if (val7.equals("1")) {
                        listBooks.Sortera();
                    } else if (val7.equals("2")) {
                        listMagazines.Sortera();
                    } else if (val7.equals("3")) {
                        listUsers.Sortera();
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