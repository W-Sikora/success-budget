package pl.wsikora.successbudget.v3.common.budget;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;


@Embeddable
@NoArgsConstructor
@Getter
public class BudgetId {

    @Column(name = "budget_id")
    private Long value;

    public BudgetId(Long value) {

        Assert.notNull(value, "BudgetId value must not be null");

        this.value = value;
    }

}