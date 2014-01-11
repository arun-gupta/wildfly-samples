<html>
    <head>
        <title>HTTP Session Failover</title>
    </head>
    <body>
        <h1>HTTP Session Failover</h1>
        
        <%= java.util.Calendar.getInstance().getTime() %><br/>
        <b>Server name:</b> <%= System.getProperty("jboss.server.name") %><br/>
        <b>Node name:</b> <%= System.getProperty("jboss.node.name") %><br/>
        <b>Host name:</b> <%= System.getProperty("jboss.host.name") %><br/>
        <b>Qualified host name:</b> <%= System.getProperty("jboss.qualified.host.name") %><br/>
        <b>Session id:</b> <%= System.getProperty("jsessionid") %><br/>
    </body>
</html>
