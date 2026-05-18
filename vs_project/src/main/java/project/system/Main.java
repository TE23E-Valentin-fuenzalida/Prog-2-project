package project.system;

import project.Basklasser.SuspendedUsers;
import project.Basklasser.Users;
import project.Listor.BooksList;
import project.Listor.SuspendedUsersList;
import project.Listor.UsersList;

import project.Listor.*;

/* 
Valentin fuenzalida ribbing
Main innehåller objekt av alla klass listor jag har och bioblotekssystemet.
Den innehåller också de olika valen jag kan göra.
Dessutom metoden för om en användare får låna eller inte låna
*/

//TODO göra upp de olika metoderna i olika files i olika packet men inte så att det är metod för metod
//TODO Kommentera med Javadoc notation och förklara mer specifikt vad varje metod gör
//TODO Sava till en fil i mina listor
//TODO behöver två nya klasser en media klass och en utlånings klass
//TODO Media klassen ska kunna ha objekt av games, films och movie med olika variabler
//TODO Utlånings klassen ska vara en put action där jag ändrar isavailable
//TODO använda Streams för att sortera på ett speciellt sätt
//TODO Lägga mer av logiken i system klassen

public class Main {
    // baseURL för skolan
/*     public static final String baseURL = "http://10.151.168.5:3148/"; */
    // baseUrl för ej skolan
       public static final String baseURL = "http://localhost:3000/";
       // skapar alla object av klasserna som jag använder
       static Biblotekssystem system = new Biblotekssystem();
    void main() {
        system.meny();

    }
}