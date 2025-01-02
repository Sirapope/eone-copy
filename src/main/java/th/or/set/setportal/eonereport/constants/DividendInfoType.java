package th.or.set.setportal.eonereport.constants;

import lombok.Getter;

@Getter
public enum DividendInfoType {

    NET_PROFIT_PER_SHARE("NET_PROFIT_PER_SHARE"),
    DIVIDEND_PER_SHARE("DIVIDEND_PER_SHARE"),
    DIVIDEND_STOCK_RATIO("DIVIDEND_STOCK_RATIO"),
    DIVIDEND_SHARE_VALUE_PER_SHARE("DIVIDEND_SHARE_VALUE_PER_SHARE");
    private final String value;

    DividendInfoType(String value) {
        this.value = value;
    }
}
