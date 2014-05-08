<%@ page import="java.util.Enumeration" %>
<html>
    <head>
        <title>HTTP Session Failover in WildFly 8</title>
    </head>
    <body>
        <h1>HTTP Session Failover in WildFly 8</h1>

        <h2>Debug Information</h2>
        <b>Timestamp:</b> <%= java.util.Calendar.getInstance().getTime() %><br/>
        <b>Node name:</b> <%= System.getProperty("jboss.node.name") %><br/>
        <b>Host name:</b> <%= System.getProperty("jboss.host.name") %><br/>
        <b>Session id:</b> <%= session.getId() %><br/>

        <%
            String attrName = request.getParameter("attrName");
            String attrValue = request.getParameter("attrValue");
            if (attrName != null &&
                    !attrValue.equals("") &&
                    attrValue != null &&
                    !attrName.equals("")) {
                out.println("\"" + attrName + "=" + attrValue + "\" added to session.");
                session.setAttribute(attrName,attrValue);
            }
        %>

        <h2>Add Session Data</h2>
        <form action="index.jsp" method="POST">
            Attribute name:
            <input type="text" size="20" name="attrName">
            <BR>
            Attribute value:
            <input type="text" size="20" name="attrValue">
            <br>
            <input type="submit" name="action" value="Add Session Data">
            <input type="submit" name="action" value="Reload Page">
        </form>

        <h2>Session Data</h2>
        <%
            Enumeration<String> atts = session.getAttributeNames();
            while (atts.hasMoreElements()) {
                String attr = atts.nextElement();
                out.println("Attribute: " + attr + ", Value: " + session.getAttribute(attr) + "<br>");
            }
        %>
    </body>
</html>
