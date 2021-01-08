package com.sun.ts.arquillian;

import org.jboss.arquillian.container.test.spi.client.deployment.ApplicationArchiveProcessor;
import org.jboss.arquillian.test.spi.TestClass;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

public class PackageHelper implements ApplicationArchiveProcessor {
    @Override
    public void process(Archive<?> archive, TestClass testClass) {
        ((JavaArchive) archive).addAsResource(new StringAsset("quarkus.rest.single-default-produces=false\nquarkus.rest.default-produces=false\nquarkus.http.root-path=/" + archive.getName().substring(0, archive.getName().length() - 4)), "application.properties");
    }
}
