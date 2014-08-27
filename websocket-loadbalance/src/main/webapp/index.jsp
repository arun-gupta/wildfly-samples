<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebSocket : Chat</title>
    </head>
    <body>
        <h1>WebSocket : Chat</h1>
        <div style="text-align: center;">
            <form action=""> 
                
                <table>
                    <tr>
                        <td>
                            Users<br/>
                            <textarea readonly="true" rows="6" cols="20" id="userField"></textarea>
                        </td>
                        <td>
                            Chat Log<br/>
                            <textarea readonly="true" rows="6" cols="50" id="chatlogField"></textarea> 
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input id="textField" name="name" value="Duke" type="text"><br>
                            <input onclick="join();" value="Join" type="button"> 
                            <input onclick="send_message();" value="Chat" type="button"> 
                        </td>
                    </tr>
                </table>

            </form>
        </div>
        Node name:</b> <%= System.getProperty("jboss.node.name") %>
        <div id="output"></div>
        <script language="javascript" type="text/javascript" src="websocket.js">
            
        </script>

    </body>
</html>
