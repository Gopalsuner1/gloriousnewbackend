package in_glorious.exceptions;

public class StudentNotFound extends RuntimeException {
    public StudentNotFound(String massage){
        super(massage);
    }
}
