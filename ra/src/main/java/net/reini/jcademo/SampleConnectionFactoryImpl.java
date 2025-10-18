package net.reini.jcademo;

import javax.naming.NamingException;
import javax.naming.Reference;
import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConnectionManager;

public class SampleConnectionFactoryImpl implements SampleConnectionFactory {
    private static final long serialVersionUID = 1L;

    private Reference reference;
    private SampleManagedConnectionFactory mcf;
    private ConnectionManager connectionManager;

    public SampleConnectionFactoryImpl(SampleManagedConnectionFactory mcf,
            ConnectionManager connectionManager) {
        this.mcf = mcf;
        this.connectionManager = connectionManager;
    }

    @Override
    public void setReference(Reference reference) {
        this.reference = reference;
    }

    @Override
    public Reference getReference() throws NamingException {
        return reference;
    }

    @Override
    public SampleConnection getConnection() throws ResourceException {
        return (SampleConnection) connectionManager.allocateConnection(mcf, null);
    }
}
