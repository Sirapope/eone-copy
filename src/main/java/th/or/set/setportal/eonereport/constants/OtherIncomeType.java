package th.or.set.setportal.eonereport.constants;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum OtherIncomeType {

    SHARE_PROFIT("SHARE_PROFIT"),
    NOT_OPER("NOT_OPER"),
    OPER("OPER");

    private String value;

    OtherIncomeType(String value) {
        this.value = value;
    }
}
