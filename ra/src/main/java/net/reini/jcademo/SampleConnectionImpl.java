package net.reini.jcademo;

import jakarta.resource.ResourceException;

public class SampleConnectionImpl implements SampleConnection {
    private SampleManagedConnection mc;
    private SampleManagedConnectionFactory mcf;

    public SampleConnectionImpl(SampleManagedConnection mc, SampleManagedConnectionFactory mcf) {
        this.mc = mc;
        this.mcf = mcf;
    }

    @Override
    public String helloWorld() { 
        return helloWorld(((SampleResourceAdapter) mcf.getResourceAdapter()).getName());
    }

    @Override
    public String helloWorld(String name) {
        return mc.helloWorld(name);
    }

    @Override
    public void close() throws ResourceException {
        mc.closeHandle(this);
    }
}
