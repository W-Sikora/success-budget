package pl.wsikora.successbudget.v3.cashflow.application.revenue;

import java.math.BigDecimal;
import java.time.YearMonth;


public interface RevenueAttributes {

    Long getRevenueId();

    YearMonth getPeriod();

    String getTitle();

    Long getCategoryId();

    String getDate();

    Integer getCurrency();

    BigDecimal getValue();

    String getPayer();

    boolean isRepeatInNextPeriod();

}
