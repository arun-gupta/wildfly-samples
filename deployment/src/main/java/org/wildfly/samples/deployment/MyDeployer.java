package org.wildfly.samples.deployment;

import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;

/**
 * @author Arun Gupta
 */
public class MyDeployer {

    public static void main(String[] args) {

        try {
            MyDeployer myDeployer = new MyDeployer();
            ModelControllerClient client = createClient(InetAddress.getByName("localhost"),
                    9999,
                    "sheldon",
                    "bazinga");
            myDeployer.readDataSources(client);
        } catch (IOException ex) {
            Logger.getLogger(MyDeployer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // /subsystem=datasources:read-resource
    private void readDataSources(ModelControllerClient client) throws IOException {
        ModelNode op = new ModelNode();
        op.get("operation").set("read-resource");

        ModelNode address = op.get("address");
        address.add("subsystem", "datasources");

        ModelNode returnVal = client.execute(op);
        System.out.println(returnVal.get("result").toString());
    }

    private void deploy(ModelControllerClient client) {

    }

    static ModelControllerClient createClient(final InetAddress host, final int port,
            final String username, final String password) {

        final CallbackHandler callbackHandler = new CallbackHandler() {

            @Override
            public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
                for (Callback current : callbacks) {
                    if (current instanceof NameCallback) {
                        NameCallback ncb = (NameCallback) current;
                        ncb.setName(username);
                    } else if (current instanceof PasswordCallback) {
                        PasswordCallback pcb = (PasswordCallback) current;
                        pcb.setPassword(password.toCharArray());
                    } else {
                        throw new UnsupportedCallbackException(current);
                    }
                }
            }
        };

        return ModelControllerClient.Factory.create(host, port, callbackHandler);
    }
}
