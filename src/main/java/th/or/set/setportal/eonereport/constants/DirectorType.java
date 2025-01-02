package th.or.set.setportal.eonereport.constants;

import lombok.Getter;
import th.or.set.setportal.eonereport.exception.NotFoundException;

@Getter
public enum DirectorType {

    ORIGINAL("ORIGINAL"),
    RE_ELECTED("RE_ELECTED"),
    NEWLY("NEWLY"),
    ADDITIONAL("ADDITIONAL");

    private final String value;

    DirectorType(String value) {
        this.value = value;
    }

    public static String getNodeConfigNameByDirectorType(DirectorType directorType) {
        switch (directorType) {
            case ORIGINAL:
                return "original";
            case RE_ELECTED:
                return "reElected";
            case NEWLY:
                return "newly";
            case ADDITIONAL:
                return "additional";
        }
        throw new NotFoundException("node found director type");
    }
}
