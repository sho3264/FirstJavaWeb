<%@page import="java.util.ArrayList"%>
<%
		if (request.getAttribute("errorLog") != null) {
			for (String a : (ArrayList<String>) request.getAttribute("errorLog")) {
	%><p>
	<font color="red"> <%=a%>
	</font>
</p>
<%
		}
		}
	%>