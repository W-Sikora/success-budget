package pl.wsikora.successbudget.v3.category.ui.view;

import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.category.application.CategoryQuery;
import pl.wsikora.successbudget.v3.common.breadcrumb.BreadcrumbElementsBuilder;
import pl.wsikora.successbudget.v3.common.util.message.MessageProvider;
import pl.wsikora.successbudget.v3.common.util.ui.ControllerDataProvider;
import pl.wsikora.successbudget.v3.common.util.ui.validation.PaginationValidator;

import java.time.YearMonth;

import static pl.wsikora.successbudget.v3.common.util.Constants.*;
import static pl.wsikora.successbudget.v3.common.util.SessionUtils.getPeriod;


@Service
class CategoryViewControllerDataProvider extends ControllerDataProvider {

    private final MessageProvider messageProvider;
    private final CategoryQuery categoryQuery;

    private CategoryViewControllerDataProvider(
        MessageProvider messageProvider,
        CategoryQuery categoryQuery
    ) {

        this.messageProvider = messageProvider;
        this.categoryQuery = categoryQuery;
    }

    ModelMap provideData(CategoryViewParameters parameters, HttpSession session) {

        Assert.notNull(parameters, "parameters must not be null");
        Assert.notNull(session, "session must not be null");

        YearMonth period = getPeriod(session);

        ModelMap modelMap = new ModelMap();

        addAttributeLogoAppUrlDashboardPath(modelMap);

        addAttributePagePathFromListView(modelMap, CATEGORY);

        addAttributeColumnSize(modelMap, 7);

        String title = messageProvider.getMessage(CATEGORY_LIST_TITLE);

        modelMap.addAttribute(PAGE_TITLE, title);

        modelMap.addAttribute(BREADCRUMB_ELEMENTS, BreadcrumbElementsBuilder.builder(messageProvider)
            .addDashboard(period)
            .add(title)
            .build());

        modelMap.addAttribute(ADD_URL, CATEGORY_ADD_PATH);

        Integer page = parameters.page();

        Integer size = parameters.size();

        if (PaginationValidator.isValid(page, size)) {

            Pageable pageable = PageRequest.of(page, size);

            modelMap.addAttribute(CURRENT_PAGE, pageable.getPageNumber());

            modelMap.addAttribute("categories", categoryQuery
                .findAll(pageable, parameters.keyword()));
        }

        return modelMap;
    }

}
