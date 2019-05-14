<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Graph Booking</title>

<style>
</style>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
    // Load the Visualization API and the piechart package.
    google.load('visualization', '1.0', {
        'packages' : [ 'corechart' ]
    });

    // Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(drawChart);

    // Callback that creates and populates a data table,
    // instantiates the pie chart, passes in the data and
    // draws it.
    function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'SportsClub');
        data.addColumn('number', 'Booking Count');
        data.addRows([
                    <c:forEach items="${bookingmap}" var="entry">
                        [ '${entry.key}', ${entry.value} ],
                    </c:forEach>
                        ['Total',0]
                    ]); 
        
        var options = {
            'title' : 'Booking Count per Club', //title which will be shown right above the Google Pie Chart
            is3D : true, //render Google Pie Chart as 3D
            pieSliceText: 'label', //on mouse hover show label or name of the Country
            tooltip :  {showColorCode: true}, // whether to display color code for a Country on mouse hover
            'width' : 900, //width of the Google Pie Chart
            'height' : 500 //height of the Google Pie Chart
        };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    }
</script>
</head>
<body>
	<div style="width: 750px">
		<div style="width: 750px"id="chart_div"></div>
	</div>
	
	<a href="admin.html">
<button class="button" ><span>Go to main page</span></button>
</a><br>
<br>

<form action="logout">

		<input type="submit" value="logout">


	</form>
</body>
</html>
















