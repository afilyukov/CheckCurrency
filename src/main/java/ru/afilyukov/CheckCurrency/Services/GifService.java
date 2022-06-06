package ru.afilyukov.CheckCurrency.Services;

import ru.afilyukov.CheckCurrency.Client.GifClient;
import ru.afilyukov.CheckCurrency.JsonParsers.GifParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service for working with gifs
 */
@Service
public class GifService {
    private final GifClient gifClient;
    private final GifParser gifParser;
    @Value("${gyphi.api.key}")
    private String appId;
    private int countOffset;

    public GifService(GifClient gifClient, GifParser gifParser) {
        this.gifClient = gifClient;
        this.gifParser = gifParser;
    }

    /**
     * The method of obtaining a gif url by name @searchName
     */
    public String getGif(String searchName) {
        int limit = 1;
        JsonNode node = gifClient.getGifRequest(appId, searchName, countOffset, limit);
        int totalCount = gifParser.getTotalCount(node);
        if (totalCount <= 0)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Gif not found");
        countOffset = (int) (Math.random() * totalCount) % 4999;
        String gifUrl = gifParser.getGifUrl(node);
        if (gifUrl == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Gif API not found");
        return gifUrl;
    }
}
