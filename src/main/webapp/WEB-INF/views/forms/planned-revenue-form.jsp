<%@include file="../imports/jsp-imports.jsp"%>

<hr class="is-invisible">

<form:form modelAttribute="plannedRevenueForm" action="${formAction}" method="post" novalidate="novalidate">

    <form:hidden path="plannedRevenueId"/>

    <form:hidden path="period"/>

    <div class="field">
        <form:label path="categoryId" cssClass="label">
            <fmt:message key="category"/>
            <fmt:message key="required.field.sign"/>
        </form:label>

        <div class="control">
            <div class="select is-fullwidth">
                <form:select path="categoryId">
                    <c:forEach items="${categories}" var="category">
                        <form:option value="${category.categoryId}" label="${category.title}"/>
                    </c:forEach>
                </form:select>
            </div>
        </div>

        <p class="validation-message">
            <form:errors path="categoryId" cssClass="has-text-danger"/>
        </p>
    </div>

    <div class="field">
        <form:label path="value" cssClass="label">
            <fmt:message key="money"/>
            <fmt:message key="required.field.sign"/>
        </form:label>

        <div class="field has-addons has-mb-10">
            <p class="control is-expanded">
                <form:input path="value" type="number" cssClass="input"/>
            </p>

            <p class="control">
                <span class="select">
                    <form:select path="currency" cssClass="select">
                        <c:forEach items="${currencies}" var="currency">
                            <form:option value="${currency.ordinal()}" label="${currency.sign}"/>
                        </c:forEach>
                    </form:select>
                </span>
            </p>
        </div>

        <p class="validation-message">
            <form:errors path="value" cssClass="has-text-danger"/>
            <form:errors path="currency" cssClass="has-text-danger"/>
        </p>
    </div>

    <div class="field">
        <form:label path="repeatInNextPeriod" cssClass="label">
            <fmt:message key="repeat.in.next.budget"/>
        </form:label>

        <div class="control">
            <label class="radio mr-3">
                <form:radiobutton path="repeatInNextPeriod" value="true"/>
                <fmt:message key="yes"/>
            </label>
            <label class="radio">
                <form:radiobutton path="repeatInNextPeriod" value="false"/>
                <fmt:message key="no"/>
            </label>
        </div>

        <p class="validation-message">
            <form:errors path="repeatInNextPeriod" cssClass="has-text-danger"/>
        </p>
    </div>

    <%@ include file="../common/required-field-info.jsp" %>

    <div class="field is-grouped is-grouped-centered">
        <div class="control mt-5">
            <button type="submit" class="button is-link is-outlined">
                <fmt:message key="save.text"/>
            </button>
        </div>
    </div>
</form:form>
