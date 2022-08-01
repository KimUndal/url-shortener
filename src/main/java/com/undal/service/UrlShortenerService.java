package com.undal.service;

import com.undal.entity.UrlShortener;
import com.undal.entity.UrlShortenerRepository;
import com.undal.exception.UrlShortenerException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
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

    @Inject
    UrlShortenerRepository urlShortenerRepository;

    @Transactional
    public List<UrlShortener> createUrlShortener(UrlShortener urlShortener){
        if(urlShortener == null) {
            throw new UrlShortenerException(URL_NOT_CREATED);
        }
        long alias = urlShortenerRepository.find("alias", urlShortener.getAlias()).count();
        if(alias > 0) {
            throw new UrlShortenerException(ALIAS_ALREADY_EXISTS);
        }
       urlShortenerRepository.persist(urlShortener);
        return urlShortenerRepository.listAll();
    }

    public Optional<UrlShortener> getUrlShortener(String alias){
        if(alias == null || alias.isBlank()){
            throw new UrlShortenerException(ALIAS_NULL);
        }

        return urlShortenerRepository.find("alias", alias).stream().findFirst();
    }
}
