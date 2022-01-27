package net.reini.jcademo;

import java.io.PrintWriter;
import java.util.Set;
import java.util.UUID;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionDefinition;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterAssociation;
import javax.security.auth.Subject;

@ConnectionDefinition(connectionFactory = SampleManagedConnectionFactory.class,
        connectionFactoryImpl = SampleConnectionFactoryImpl.class,
        connection = SampleConnection.class, connectionImpl = SampleConnectionImpl.class)
public class SampleManagedConnectionFactory
        implements ManagedConnectionFactory, ResourceAdapterAssociation {
    private static final long serialVersionUID = 1L;

    private final UUID id;

    private ResourceAdapter ra;
    private PrintWriter logwriter;

    public SampleManagedConnectionFactory() {
        id = UUID.randomUUID();
    }

    @Override
    public ResourceAdapter getResourceAdapter() {
        return ra;
    }

    @Override
    public void setResourceAdapter(ResourceAdapter ra) {
        this.ra = ra;
    }

    @Override
    public Object createConnectionFactory() throws ResourceException {
        throw new ResourceException(
                "This resource adapter doesn't support non-managed environments");
    }

    @Override
    public Object createConnectionFactory(ConnectionManager cxManager) throws ResourceException {
        return new SampleConnectionFactoryImpl(this, cxManager);
    }

    @Override
    public ManagedConnection createManagedConnection(Subject subject,
            ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        return new SampleManagedConnection(this);
    }

    @Override
    public ManagedConnection matchManagedConnections(
            @SuppressWarnings("rawtypes") Set connectionSet, Subject subject,
            ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        @SuppressWarnings("unchecked")
        Set<ManagedConnection> managedConnectionSet = connectionSet;
        for (ManagedConnection mc : managedConnectionSet) {
            if (mc instanceof SampleManagedConnection) {
                return mc;
            }
        }
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws ResourceException {
        logwriter = out;
    }

    @Override
    public PrintWriter getLogWriter() throws ResourceException {
        return logwriter;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SampleManagedConnectionFactory) {
            return id.equals(((SampleManagedConnectionFactory) obj).id);
        }
        return false;
    }
}
