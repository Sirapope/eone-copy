package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum PotentialFactorAffectFinancialConditionType {

    FUTURE("FUTURE"),
    PROJECT_RESEARCH("PROJECT_RESEARCH"),
    OTHER("OTHER");

    private final String value;

    PotentialFactorAffectFinancialConditionType(String value) {
        this.value = value;
    }
}
