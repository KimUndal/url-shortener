package com.undal;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class UrlShortenerService {

    @ConfigProperty(name="exception.message.url_not_created")
    public String URL_NOT_CREATED;
    @ConfigProperty(name="exception.message.alias_already_exists")
    public String ALIAS_ALREADY_EXISTS;
    @ConfigProperty(name="exception.message.alias_null")
    public String ALIAS_NULL;
    private final List<UrlShortener> urlShortenerList = new ArrayList<>();

    public List<UrlShortener> createUrlShortener(UrlShortener urlShortener){
        if(urlShortener == null) {
            throw new UrlShortenerException(URL_NOT_CREATED);
        }
        long alias = urlShortenerList
                .stream()
                .filter(url ->
                        url.getAlias().equals(urlShortener.getAlias())
                )
                .count();
        if(alias > 0) {
            throw new UrlShortenerException(ALIAS_ALREADY_EXISTS);
        }
        urlShortenerList.add(urlShortener);
        return urlShortenerList;
    }

    public Optional<UrlShortener> getUrlShortener(String alias){
        if(alias == null || alias.isBlank()){
            throw new UrlShortenerException(ALIAS_NULL);
        }

        return urlShortenerList
                .stream()
                .filter(url -> url.getAlias().equals(alias))
                .findFirst();
    }
}
