package pl.wsikora.successbudget.category.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pl.wsikora.successbudget.abstractutil.domain.AbstractEntity;


@Table
@Entity(name = "categories")
public class Category extends AbstractEntity {

    private Long creatorId;

    private String name;

    private Long parentCategoryId;

    private Long colorId;

    private Long iconId;

    public Category() {

    }

    public Long getCreatorId() {

        return creatorId;
    }

    public void setCreatorId(Long creatorId) {

        this.creatorId = creatorId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Long getParentCategoryId() {

        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {

        this.parentCategoryId = parentCategoryId;
    }

    public Long getColorId() {

        return colorId;
    }

    public void setColorId(Long colorId) {

        this.colorId = colorId;
    }

    public Long getIconId() {

        return iconId;
    }

    public void setIconId(Long iconId) {

        this.iconId = iconId;
    }


}
