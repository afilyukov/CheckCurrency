package ru.afilyukov.CheckCurrency.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Gyphi API inferface
 */
@FeignClient(url = "${gyphi.api.path}", name = "${gyphi.feign.client.name}")
public interface GifClient {

    /**
     * The requesting method  to get a gif by search query
     */
    @GetMapping("${gyphi.api.path.search}")
    JsonNode getGifRequest(@RequestParam(name = "api_key") String appId,
                                  @RequestParam(name = "q") String searchName,
                                  @RequestParam(name = "offset") int offset,
                                  @RequestParam(name = "limit") int limit);

}
