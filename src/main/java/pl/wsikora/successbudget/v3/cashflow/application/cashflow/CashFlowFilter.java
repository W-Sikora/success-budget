package pl.wsikora.successbudget.v3.cashflow.application.cashflow;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.time.LocalDate;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


public record CashFlowFilter(Pageable pageable,
                             Long cashFlowId,
                             @Nullable String keyword,
                             @Nullable Long categoryId,
                             @Nullable LocalDate fromDate,
                             @Nullable LocalDate toDate) {

    public CashFlowFilter {

        Assert.notNull(pageable, "pageable must not be null");
        Assert.notNull(cashFlowId, "cashFlowId must not be null");
        Assert.isTrue(areDatesValid(fromDate, toDate), "dates must be valid");

    }

    private boolean areDatesValid(LocalDate fromDate, LocalDate toDate) {

        if (isNull(fromDate) && nonNull(toDate)) {

            return false;
        }

        return isNull(fromDate) || nonNull(toDate);
    }

}