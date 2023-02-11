package pl.wsikora.successbudget.v3.common.type.money;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.type.currency.CurrencyValidator;
import pl.wsikora.successbudget.v3.common.util.validation.AbstractFormValidator;

import java.math.BigDecimal;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;


@Service
public class MoneyFormValidator extends AbstractFormValidator<MoneyForm> {

    static final String F_VALUE = "value";

    private final CurrencyValidator currencyValidator;

    MoneyFormValidator(CurrencyValidator currencyValidator) {

        this.currencyValidator = currencyValidator;
    }

    @Override
    public void validateForm(MoneyForm moneyForm, Errors errors) {

        currencyValidator.validateForm(moneyForm.getCurrencyId(), errors);

        BigDecimal value = moneyForm.getValue();

        if (isNull(value)) {

            errors.rejectValue(F_VALUE, E_FIELD_MUST_NOT_BE_EMPTY);
        }
        else if (!Money.hasValidValue(value)) {

            errors.rejectValue(F_VALUE, E_VALUE_MUST_BE_WITHIN_ALLOWED_RANGE,
                Money.getRange(), EMPTY);
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(MoneyForm.class);
    }

}