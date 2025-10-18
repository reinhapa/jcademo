package net.reini.jcademo;

import static jakarta.resource.spi.TransactionSupport.TransactionSupportLevel.NoTransaction;

import java.util.Objects;

import jakarta.resource.ResourceException;
import jakarta.resource.spi.ActivationSpec;
import jakarta.resource.spi.BootstrapContext;
import jakarta.resource.spi.ConfigProperty;
import jakarta.resource.spi.Connector;
import jakarta.resource.spi.ResourceAdapter;
import jakarta.resource.spi.ResourceAdapterInternalException;
import jakarta.resource.spi.endpoint.MessageEndpointFactory;
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
