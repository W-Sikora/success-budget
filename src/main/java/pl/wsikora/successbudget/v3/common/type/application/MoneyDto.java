package pl.wsikora.successbudget.v3.common.type.application;

import lombok.Value;

import java.math.BigDecimal;


@Value
public class MoneyDto {

    Long currencyId;
    BigDecimal value;

}
