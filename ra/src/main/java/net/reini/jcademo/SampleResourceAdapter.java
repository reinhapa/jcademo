package net.reini.jcademo;

import static javax.resource.spi.TransactionSupport.TransactionSupportLevel.NoTransaction;

import java.util.Objects;

import javax.resource.ResourceException;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.ConfigProperty;
import javax.resource.spi.Connector;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;

@Connector(reauthenticationSupport = false, transactionSupport = NoTransaction)
public class SampleResourceAdapter implements ResourceAdapter {
    @ConfigProperty(defaultValue = "WildFly", supportsDynamicUpdates = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void start(BootstrapContext ctx) throws ResourceAdapterInternalException {
        // no op
    }

    @Override
    public void stop() {
        // no op
    }

    @Override
    public void endpointActivation(MessageEndpointFactory endpointFactory, ActivationSpec spec)
            throws ResourceException {
        // no op
    }

    @Override
    public void endpointDeactivation(MessageEndpointFactory endpointFactory, ActivationSpec spec) {
        // no op
    }

    @Override
    public XAResource[] getXAResources(ActivationSpec[] specs) throws ResourceException {
        return null;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SampleResourceAdapter) {
            SampleResourceAdapter other = (SampleResourceAdapter) obj;
            return Objects.equals(name, other.name);
        }
        return false;
    }
}
