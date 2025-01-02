package th.or.set.setportal.eonereport.exception;


import lombok.Data;

@Data
public class InformationNotFoundException extends RuntimeException {

    private String code;

    public InformationNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }
}
