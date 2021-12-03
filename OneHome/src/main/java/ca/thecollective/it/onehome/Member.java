package ca.thecollective.it.onehome;

public class Member {


    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public String email(String message){
        return message;
    }

    public String name(String name){
        return name;
    }

    public String rating(String rating){
        return rating;
    }

    public String comment(String comment){
        return comment;
    }

    public Member() {
    }

    private String email,name, comment,rating;
    private Long number;



}
