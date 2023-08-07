public class Book {
    private String title;
    private String author;
    private String genre;
    private int yearPublished;
    public Book(){

    }
    public Book(String title, String author, String genre, int yearPublished){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearPublished = yearPublished;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getAuthor(){
        return this.author;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public String getGenre(){
        return this.genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public int getYearPublished(){
        return this.yearPublished;
    }
    public void setYearPublished(int yearPublished){
        this.yearPublished = yearPublished;
    }
    @Override
    public String toString(){
        return "Book Title : " + this.getTitle() + ", Book Author : " + this.getAuthor() + ", Book Genre : " + this.getGenre() + ", Publish Year : " + this.getYearPublished();
    }
}
