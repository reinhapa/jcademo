package net.reini.jcademo;

import jakarta.resource.ResourceException;
import jakarta.resource.spi.ManagedConnectionMetaData;

public class SampleManagedConnectionMetaData implements ManagedConnectionMetaData {
    @Override
    public String getEISProductName() throws ResourceException {
        return "Sample Resource Adapter";
    }

    @Override
    public String getEISProductVersion() throws ResourceException {
        return "1.0";
    }

    @Override
    public int getMaxConnections() throws ResourceException {
        return 0;
    }

    @Override
    public String getUserName() throws ResourceException {
        return null;
    }
}
