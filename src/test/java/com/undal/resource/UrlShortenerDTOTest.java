package com.undal.resource;

import com.undal.resource.UrlShortenerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class UrlShortenerDTOTest {

    private UrlShortenerDTO urlShortenerDTO;

    @BeforeEach
    void setUp() {
        try {
            urlShortenerDTO = new UrlShortenerDTO("alias", new URI("http://myurl"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void urlShortenerNotNull(){
        assertNotNull(urlShortenerDTO);
    }

    @Test
    void getAlias() {
        assertEquals("alias", urlShortenerDTO.getAlias());
    }

    @Test
    void setAlias() {
        urlShortenerDTO.setAlias("newAlias");
        assertEquals("newAlias", urlShortenerDTO.getAlias());
    }

    @Test
    void getUrl() {
        assertEquals("http://myurl", urlShortenerDTO.getUrl().toString());
    }

    @Test
    void setUrl() {
        try {
            urlShortenerDTO.setUrl(new URI("http://newUrl"));
            assertEquals("http://newUrl", urlShortenerDTO.getUrl().toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}