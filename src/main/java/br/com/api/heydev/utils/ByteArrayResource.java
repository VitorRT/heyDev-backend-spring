package br.com.api.heydev.utils;

import org.springframework.core.io.AbstractResource;
import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ByteArrayResource extends AbstractResource {
    private final byte[] byteArray;
    private final String description;

    public ByteArrayResource(byte[] byteArray, String description) {
        Assert.notNull(byteArray, "Byte array must not be null");
        this.byteArray = byteArray;
        this.description = (description != null) ? description : "";
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(this.byteArray);
    }

    @Override
    public boolean exists() {
        return (this.byteArray != null);
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public boolean isReadable() {
        return true;
    }

    @Override
    public long contentLength() {
        return Objects.requireNonNull(byteArray).length;
    }

    @Override
    public long lastModified() {
        return -1;
    }
}
