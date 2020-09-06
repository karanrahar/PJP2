<%@ include file="common/header.jspf"%>
<div class="container">
		<h1>Date Time Calculator</h1>
		<h2>Choose from the list of operations</h2>
		<form action="/calculate" method="get">
			<input type="radio" name="operation" value="1"> Add/Subtract Two Dates
			<br/><br/>
			<input type="radio" name="operation" value="2"> Add/Subtract Days From Date
			<br/><br/>
			<input type="radio" name="operation" value="3"> Add/Subtract Weeks From Date
			<br/><br/>
			<input type="radio" name="operation" value="4"> Add/Subtract Months From Date
			<br/><br/>
			<input type="radio" name="operation" value="5"> Add/Subtract Years From Date
			<br/><br/>
			<input type="radio" name="operation" value="6"> Get Day Of The Week For Date
			<br/><br/>
			<input type="radio" name="operation" value="7"> Get Week Number For Date
			<br/><br/>
			<input type="radio" name="operation" value="8"> Get Date From Phrase
			<br/><br/>
			<input class="btn btn-success" type="submit" value="Submit">
		</form>
</div>
<%@ include file="common/footer.jspf"%>