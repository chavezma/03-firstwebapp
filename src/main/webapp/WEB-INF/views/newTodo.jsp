<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<h1>Create a new Todo's:</h1>
	<hr>
	<form:form method="POST" modelAttribute="todo">
		<fieldset class="mb-3">
			<form:label path="description">Description</form:label>
			<form:input type="text" path="description"/>
			<form:errors path="description" cssClass="text-danger"/>
		</fieldset>

		<fieldset class="mb-3">
			<form:label path="targetDate">Target Date</form:label>
			<form:input type="text" path="targetDate"/>
			<form:errors path="targetDate" cssClass="text-danger"/>
		</fieldset>

		<form:input type="hidden" path="id"/>

		<form:input type="hidden" path="done"/>

		<br>
		<br>
		<input type="submit" class="btn btn-success"/>
	</form:form>
</div>
<%@ include file="common/footer.jspf" %>
<script type="text/javascript">
	$('#targetDate').datepicker({
		format: 'yyyy-mm-dd'
	});
</script>