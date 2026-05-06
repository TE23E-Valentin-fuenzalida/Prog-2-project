package project;
/* 
Valentin fuenzalida ribbing
library innehåller variabler med en konstuktor och getters.
*/
public abstract class library {
    // variabler
    protected String id;
    protected String title;
    protected boolean isAvailable;

    // konstruktor för library
    public library(String id, String title, boolean isAvailable){
        this.id=id;
        this.title=title;
        this.isAvailable=isAvailable;
    }

    // getters
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public boolean getIsAvailable(){
        return true;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "id: "+id+"\n title: "+title+"\n isAvailable: "+isAvailable+"\n";
    }
}
