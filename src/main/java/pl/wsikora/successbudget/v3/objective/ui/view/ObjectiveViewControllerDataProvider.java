package pl.wsikora.successbudget.v3.objective.ui.view;

import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.breadcrumb.BreadcrumbElementsBuilder;
import pl.wsikora.successbudget.v3.common.util.message.MessageProvider;
import pl.wsikora.successbudget.v3.common.util.ui.ControllerDataProvider;
import pl.wsikora.successbudget.v3.common.util.ui.validation.PaginationValidator;
import pl.wsikora.successbudget.v3.objective.application.ObjectiveQuery;

import java.time.YearMonth;

import static pl.wsikora.successbudget.v3.common.util.Constants.*;
import static pl.wsikora.successbudget.v3.common.util.SessionUtils.getPeriod;


@Service
class ObjectiveViewControllerDataProvider extends ControllerDataProvider {

    private final MessageProvider messageProvider;
    private final ObjectiveQuery objectiveQuery;

    private ObjectiveViewControllerDataProvider(
        MessageProvider messageProvider,
        ObjectiveQuery objectiveQuery
    ) {

        this.messageProvider = messageProvider;
        this.objectiveQuery = objectiveQuery;
    }

    ModelMap provideData(ObjectiveViewParameters parameters, HttpSession session) {

        Assert.notNull(session, "session must not be null");

        YearMonth period = getPeriod(session);

        ModelMap modelMap = new ModelMap();

        addAttributeLogoAppUrlDashboardPath(modelMap);

        addAttributePagePathFromListView(modelMap, OBJECTIVE);

        addAttributeColumnSize(modelMap, 10);

        String title = messageProvider.getMessage(OBJECTIVE_LIST_TITLE);

        modelMap.addAttribute(PAGE_TITLE, title);

        modelMap.addAttribute(BREADCRUMB_ELEMENTS, BreadcrumbElementsBuilder.builder(messageProvider)
            .addDashboard(period)
            .add(title)
            .build());

        modelMap.addAttribute(ADD_URL, OBJECTIVE_ADD_PATH);

        Integer page = parameters.page();

        Integer size = parameters.size();

        if (PaginationValidator.isValid(page, size)) {

            Pageable pageable = PageRequest.of(page, size);

            modelMap.addAttribute(CURRENT_PAGE, pageable.getPageNumber());

            modelMap.addAttribute("objectives", objectiveQuery.findAll(
                pageable, parameters.keyword()));
        }

        return modelMap;
    }

}
