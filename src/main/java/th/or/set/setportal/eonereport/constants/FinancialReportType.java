package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum FinancialReportType {

    BodFinancialResponsibilityReport("BodFinancialResponsibilityReport"),
    AuditorsReport("AuditorsReport"),
    FinancialStatements("FinancialStatements"),
    NotesToFinancialStatements("NotesToFinancialStatements");

    private final String value;

    FinancialReportType(String value) {
        this.value = value;
    }
}
