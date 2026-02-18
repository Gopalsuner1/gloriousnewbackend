package in_glorious.exceptions;

public class StudentExist extends RuntimeException{
    public StudentExist(String massage){
        super(massage);
    }
}
