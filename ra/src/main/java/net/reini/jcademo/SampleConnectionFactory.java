package net.reini.jcademo;

import java.io.Serializable;

import jakarta.resource.Referenceable;
import jakarta.resource.ResourceException;

public interface SampleConnectionFactory extends Serializable, Referenceable{
    /** 
     * Get connection from factory
     *
     * @return HelloWorldConnection instance
     * @exception ResourceException Thrown if a connection can't be obtained
     */
    public SampleConnection getConnection() throws ResourceException;
}
