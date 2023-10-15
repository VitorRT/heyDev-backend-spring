package br.com.api.heydev.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FileFinder {
    @Autowired
    private ResourceLoader loader;

    public byte[] getFileByName(String fileName) throws IOException {
        Resource resource = loader.getResource("classpath:static/assets/" + fileName);
        if (resource.exists()) {
            try (InputStream inputStream = resource.getInputStream()) {
                return StreamUtils.copyToByteArray(inputStream);
            }
        } else {
            throw new IOException("A imagem não pôde ser encontrada: " + fileName);
        }
    }
}
