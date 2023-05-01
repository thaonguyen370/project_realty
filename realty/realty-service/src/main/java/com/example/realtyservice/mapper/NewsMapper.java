package com.example.realtyservice.mapper;

import com.example.realtydto.dto.ImageDto;
import com.example.realtydto.dto.NewsDto;
import com.example.realtyservice.entity.Images;
import com.example.realtyservice.entity.News;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface NewsMapper {
    @Mapping(target = "images", ignore = true)
    NewsDto newsToNewsDto(News news);

    @Mapping(target = "images", ignore = true)
    News newsDtoToNews(NewsDto newsDto);


    @AfterMapping
    default void editEntity(NewsDto newsDto, @MappingTarget News news) {
        if (newsDto.getImages() != null && !newsDto.getImages().isEmpty()) {
            newsDto.getImages().forEach(i->{
                Images images=new Images();
                images.setLinkDownload(i.getLinkDownload());
                images.setLinkView(i.getLinkView());
                images.setNews(news);
                news.getImages().add(images);
            });
        }
    }
    @AfterMapping
    default void editDto(News news,@MappingTarget NewsDto newsDto) {
        if (news.getImages() != null && !news.getImages().isEmpty()) {
            news.getImages().forEach(i->{
                ImageDto images=new ImageDto();
                images.setLinkDownload(i.getLinkDownload());
                images.setLinkView(i.getLinkView());
                newsDto.getImages().add(images);
            });
        }
    }
}
