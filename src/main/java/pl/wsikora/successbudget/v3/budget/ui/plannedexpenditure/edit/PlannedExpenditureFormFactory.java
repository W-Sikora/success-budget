package pl.wsikora.successbudget.v3.budget.ui.plannedexpenditure.edit;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.budget.application.plannedexpenditure.PlannedExpenditureDto;
import pl.wsikora.successbudget.v3.budget.application.plannedexpenditure.PlannedExpenditureQuery;
import pl.wsikora.successbudget.v3.common.money.MoneyDto;

import java.util.Optional;


@Service
class PlannedExpenditureFormFactory {

    private final PlannedExpenditureQuery plannedExpenditureQuery;

    private PlannedExpenditureFormFactory(PlannedExpenditureQuery plannedExpenditureQuery) {

        this.plannedExpenditureQuery = plannedExpenditureQuery;
    }

    PlannedExpenditureForm getPlannedExpenditureForm(Long budgetId, @Nullable Long plannedExpenditureId) {

        Assert.notNull(budgetId, "budgetId must not be null");

        return Optional.ofNullable(plannedExpenditureId)
            .flatMap(plannedExpenditureQuery::findByPlannedExpenditureId)
            .map(this::toForm)
            .orElseGet(() -> newForm(budgetId));
    }

    private PlannedExpenditureForm toForm(PlannedExpenditureDto plannedExpenditureDto) {

        MoneyDto moneyDto = plannedExpenditureDto.getMoneyDto();

        return PlannedExpenditureForm.builder()
            .plannedExpenditureId(plannedExpenditureDto.getPlannedExpenditureId())
            .budgetId(plannedExpenditureDto.getBudgetId())
            .categoryId(plannedExpenditureDto.getCategoryDto().getCategoryId())
            .priorityId(plannedExpenditureDto.getPriorityId())
            .currencyId(moneyDto.getCurrencyId())
            .value(moneyDto.getValue())
            .repeatInNextPeriod(plannedExpenditureDto.isRepeatInNextPeriod())
            .build();
    }

    private PlannedExpenditureForm newForm(Long budgetId) {

        return PlannedExpenditureForm.builder()
            .budgetId(budgetId)
            .build();
    }

}