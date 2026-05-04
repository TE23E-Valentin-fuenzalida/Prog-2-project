package project;

public class Magazines extends library {
    // variabler
    private int issueNumber;
    private String catogory;
    private int publishedYear;

    //konstruktor för Magazines
    public Magazines(String id, String title, boolean isAvailable, int issueNumber, String catogory, int publishedYear){
        super(id, title, isAvailable);
        this.issueNumber=issueNumber;
        this.catogory=catogory;
        this.publishedYear=publishedYear;
    }

    // getters
    public String getCatogory() {
        return catogory;
    }
    public int getIssueNumber() {
        return issueNumber;
    }
    public int getPublishedYear() {
        return publishedYear;
    }
    
    // setters
    public void setCatogory(String catogory) {
        this.catogory = catogory;
    }
    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

}
