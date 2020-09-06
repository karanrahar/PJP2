<%@ include file="common/header.jspf"%>
<div class="container">
		<h2>Add/Subtract Two Dates</h2>
		<form:form action="/calculate"  method="post" commandName="calculatorDetails">
			
			<input type="hidden" name="choice" value="1" readonly>
			<fieldset class="form-group" >
				<form:label path="date1">Enter Date 1</form:label>
				<form:input path="date1" type="text" required="required"/>
				<br><br>
				<form:label path="date2">Enter Date 2</form:label>
				<form:input path="date2" type="text" required="required"/>
				<br><br>
				<form:label path="operation">Enter (+/-)</form:label>
				<form:input path="operation" type="text"/>
				
				<br><br>
			</fieldset>
			<input class="btn btn-success" type="submit" value="Calculate">
		</form:form>
		<br><br>
		
		<a href="/menuOptions">Back to menu</a>
</div>
<%@ include file="common/footer.jspf"%>