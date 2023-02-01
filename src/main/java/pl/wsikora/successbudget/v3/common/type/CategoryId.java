package pl.wsikora.successbudget.v3.common.type;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.io.Serializable;


@Getter
@NoArgsConstructor
@Embeddable
public class CategoryId implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long value;

    public CategoryId(Long value) {

        Assert.notNull(value, "CategoryId value must not be null");

        this.value = value;
    }
}