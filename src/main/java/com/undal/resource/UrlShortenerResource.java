package com.undal.resource;

import com.undal.*;
import com.undal.entity.UrlShortener;
import com.undal.exception.UrlShortenerException;
import com.undal.service.UrlShortenerService;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

public class UrlShortenerResource implements UrlShortenerApi {
    @ConfigProperty(name = "exception.message.url_not_created")
    public String URL_NOT_CREATED;

    @Inject
    UrlShortenerService urlShortenerService;

    @Inject
    UrlShortenerMapper urlShortenerMapper;

    @Override
    public Response getUrlShortener(String alias) {
       return urlShortenerService.getUrlShortener(alias).map(url ->
                Response.status(Response.Status.MOVED_PERMANENTLY).
                        header("location", url.getUrl())
                        .build()
        ).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Override
    public Response createUrlShortener(UrlShortenerDTO urlShortenerDTO) {
        if (urlShortenerDTO == null) {
            throw new UrlShortenerException(URL_NOT_CREATED);
        }
        UrlShortener urlShortener = urlShortenerMapper.toDAO(urlShortenerDTO);

        return Response.ok(urlShortenerService
                .createUrlShortener(urlShortener)
                .stream()
                .map(url ->
                        urlShortenerMapper
                                .toDTO(url))
                .collect(Collectors.toList()))
                .build();
    }
}
