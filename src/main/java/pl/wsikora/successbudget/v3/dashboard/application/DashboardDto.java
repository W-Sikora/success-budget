package pl.wsikora.successbudget.v3.dashboard.application;

import lombok.Value;
import pl.wsikora.successbudget.v3.common.category.CategoryDto;
import pl.wsikora.successbudget.v3.common.money.MoneyDto;

import java.util.Map;


@Value
public class DashboardDto {

    String period;
    boolean previous;
    AggregateRevenueDto aggregateRevenueDto;
    AggregateBalanceDto aggregateBalanceDto;
    AggregateExpenditureDto aggregateExpenditureDto;
    Map<CategoryDto, MoneyDto> percentageOfExpenditureInCategoryDto;
    Map<CategoryDto, ExpenditureSummaryDto> expenditureInCategoryDto;
//    ExpenditureDto unnecessaryExpenditureDto;
//    AdviceDto adviceDto;

}