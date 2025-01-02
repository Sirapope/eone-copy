package th.or.set.setportal.eonereport.constants;

import lombok.Getter;
import th.or.set.setportal.eonereport.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static th.or.set.setportal.eonereport.config.Constants.*;

@Getter
public enum FinancialAccountType {

    //financial statement
    BS_A("BS_A"),
    BS_L("BS_L"),
    BS_OE("BS_OE"),
    PL("PL"),
    CF("CF"),

    //financial ratio
    Liquidity("Liquidity"),
    Profitability("Profitability"),
    Financial_Policy("Financial_Policy"),
    Asset_Quality("Asset_Quality"),
    Efficiency("Efficiency"),
    Other_Ratio("Other_Ratio");

    private final String value;

    FinancialAccountType(String value) {
        this.value = value;
    }

    public static FinancialAccountType getFinancialAccountTypeByNodeConfigName(String nodeConfigName) {
        switch (nodeConfigName) {
            case ASSETS:
                return BS_A;
            case LIABILITIES:
                return BS_L;
            case EQUITIES:
                return BS_OE;
            case STATEMENT_OF_COMPREHENSIVE_INCOME:
                return PL;
            case CASH_FLOW_STATEMENT:
                return CF;
            case LIQUIDITY:
                return Liquidity;
            case PROFITABILITY:
                return Profitability;
            case FINANCIAL_POLICY:
                return Financial_Policy;
            case ASSET_QUALITY:
                return Asset_Quality;
            case EFFICIENCY:
                return Efficiency;
            case OTHER_RATIO:
                return Other_Ratio;
        }
        throw new NotFoundException("not found account type");
    }

    public static FinancialAccountType getFinancialAccountTypeByAccountType(String accountType) {
        switch (accountType) {
            case "BS_A":
                return BS_A;
            case "BS_L":
                return BS_L;
            case "BS_OE":
                return BS_OE;
            case "PL":
                return PL;
            case "CF":
                return CF;
            case "liquidity":
                return Liquidity;
            case "profitability":
                return Profitability;
            case "financialPolicy":
                return Financial_Policy;
            case "assetQuality":
                return Asset_Quality;
            case "efficiency":
                return Efficiency;
            case "otherFinancialRatio":
                return Other_Ratio;
        }
        throw new NotFoundException("not found account type : |" + accountType + "|");
    }

    public static boolean getFinancialAccountTypeByAccountTypePhase4(String accountType) {
        List<String> financialAccountTypeList = List.of(
                BS_A.value,
                BS_L.value,
                BS_OE.value,
                PL.value,
                CF.value);

        return financialAccountTypeList.contains(accountType);
    }

    public static Boolean checkIsFinancialRatio(FinancialAccountType financialAccountType) {
        List<FinancialAccountType> financialRatioAccountType = Arrays.asList(
                Liquidity,
                Profitability,
                Financial_Policy,
                Asset_Quality,
                Efficiency,
                Other_Ratio);
        return financialRatioAccountType.contains(financialAccountType);
    }
}
