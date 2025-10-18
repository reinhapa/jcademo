package net.reini.jcademo;

import jakarta.resource.ResourceException;

public interface SampleConnection extends AutoCloseable {

    String helloWorld();

    String helloWorld(String name);

    @Override
    void close() throws ResourceException;
}
