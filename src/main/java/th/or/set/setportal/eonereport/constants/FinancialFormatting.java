package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum FinancialFormatting {

    B("B"),
    B_Fix("B_Fix"),
    BU("BU"),
    blank("blank");

    private final String value;

    FinancialFormatting(String value) {
        this.value = value;
    }

    public static FinancialFormatting getFinancialFormattingByFormatting(String formatting) {
        switch (formatting) {
            case "B":
                return B;
            case "B Fix":
                return B_Fix;
            case "BU":
                return BU;
            default:
                return blank;
        }
    }
}
