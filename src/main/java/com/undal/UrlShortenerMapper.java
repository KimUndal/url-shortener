package com.undal;

import com.undal.entity.UrlShortener;
import com.undal.resource.UrlShortenerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UrlShortenerMapper {

    UrlShortener toDAO(UrlShortenerDTO dto);

    UrlShortenerDTO toDTO(UrlShortener dao);
}
