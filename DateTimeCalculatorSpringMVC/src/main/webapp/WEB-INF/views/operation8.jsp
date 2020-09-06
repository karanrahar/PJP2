<%@ include file="common/header.jspf"%>
<div class="container">
		<h2>Get Date From Phrase</h2>
		<form:form action="/calculate" method="post" commandName="calculatorDetails">
			
			<input type="hidden" name="choice" value="8" readonly>
			<fieldset class="form-group">
				<form:label path="phrase">Enter Natural Language Phrase</form:label>
				<form:input path="phrase" type="text" required="required"/>
				<br><br>
			</fieldset>
			<input class="btn btn-success" type="submit" value="Calculate">
		</form:form>
		<br><br>
		
		<a href="/menuOptions">Back to menu</a>
</div>
<%@ include file="common/footer.jspf"%>