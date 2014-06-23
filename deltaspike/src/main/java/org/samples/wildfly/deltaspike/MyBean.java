package org.samples.wildfly.deltaspike;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletRequest;
import org.apache.deltaspike.core.api.common.DeltaSpike;

/**
 * @author arungupta
 */
@RequestScoped
public class MyBean {
    @Inject @DeltaSpike
    private ServletRequest request;
    
    public String giveMeHostAndPort() {
        return request.getServerName() + ":" + request.getServerPort();
    }
}
