package th.or.set.setportal.eonereport.constants;

import lombok.Getter;
import th.or.set.setportal.eonereport.exception.NotFoundException;

@Getter
public enum FinancialDataType {

    T("T"),
    N("N"),
    R("R"),
    NV("NV");

    private final String value;

    FinancialDataType(String value) {
        this.value = value;
    }

    public static FinancialDataType getFinancialDataTypeByDatatype(String dataType) {
        for (FinancialDataType f : FinancialDataType.values()) {
            if (f.value.equalsIgnoreCase(dataType)) {
                return f;
            }
        }
        throw new NotFoundException("not found data type : " + dataType);
    }
}
