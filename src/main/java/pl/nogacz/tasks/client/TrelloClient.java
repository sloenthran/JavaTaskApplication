package pl.nogacz.tasks.client;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.nogacz.tasks.config.TrelloConfig;
import pl.nogacz.tasks.domain.CreatedTrelloCard;
import pl.nogacz.tasks.domain.dto.TrelloBoardDto;
import pl.nogacz.tasks.domain.dto.TrelloCardDto;

import java.net.URI;
import java.util.*;

/**
 * @author Dawid Nogacz on 02.08.2019
 */
@Component
@AllArgsConstructor
public class TrelloClient {
    private RestTemplate restTemplate;
    private TrelloConfig trelloConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    public List<TrelloBoardDto> getTrelloBoards() {
        HashMap<String, String> params = new HashMap<>();

        params.put("fields", "name,id");
        params.put("lists", "all");

        URI url = generateUrl("/members/" + trelloConfig.getTrelloUser() + "/boards", params);

        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

            return Optional.ofNullable(boardsResponse)
                    .map(response -> Arrays.asList(response))
                    .orElse(new ArrayList<>());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {
        HashMap<String, String> params = new HashMap<>();

        params.put("name", trelloCardDto.getName());
        params.put("desc", trelloCardDto.getDescription());
        params.put("pos", trelloCardDto.getPosition());
        params.put("idList", trelloCardDto.getListId());

        URI url = generateUrl("/cards", params);

        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }

    private URI generateUrl(String host, HashMap<String, String> params) {
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndPoint() + host);

        url.queryParam("key", trelloConfig.getTrelloAppKey());
        url.queryParam("token", trelloConfig.getTrelloAppToken());

        for (Map.Entry<String, String> value : params.entrySet()) {
            url.queryParam(value.getKey(), value.getValue());
        }

        return url.build().encode().toUri();
    }
}