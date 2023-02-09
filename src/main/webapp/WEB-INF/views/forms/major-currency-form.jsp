<%@include file="../imports/jsp-imports.jsp"%>

<hr class="is-invisible">

<form:form modelAttribute="form" action="${formAction}" method="post">
    <div class="field">
        <form:label path="majorCurrencyId" cssClass="label">
            <fmt:message key="major.currency"/>
            <fmt:message key="required.field.sign"/>
        </form:label>

        <div class="select is-fullwidth">
            <form:select path="majorCurrencyId" cssClass="select" cssErrorClass="select is-danger">
                <c:forEach items="${currencies}" var="currencyId">
                    <form:option value="${currencyId}">
                        <fmt:message key="currency.${currencyId}"/>
                    </form:option>
                </c:forEach>
            </form:select>
        </div>

        <p class="validation-message">
            <form:errors path="majorCurrencyId" cssClass="has-text-danger"/>
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