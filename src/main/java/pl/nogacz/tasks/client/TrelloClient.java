package pl.nogacz.tasks.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.nogacz.tasks.domain.CreatedTrelloCard;
import pl.nogacz.tasks.domain.dto.TrelloBoardDto;
import pl.nogacz.tasks.domain.dto.TrelloCardDto;

import java.net.URI;
import java.util.*;

/**
 * @author Dawid Nogacz on 02.08.2019
 */
@Component
public class TrelloClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndPoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloAppToken;

    @Value("${trello.user}")
    private String trelloUser;

    public List<TrelloBoardDto> getTrelloBoards() {
        HashMap<String, String> params = new HashMap<>();

        params.put("fields", "name,id");
        params.put("lists", "all");

        URI url = generateUrl("/members/" + trelloUser + "/boards", params);

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        return  Optional.ofNullable(boardsResponse)
                .map(response -> Arrays.asList(response))
                .orElse(new ArrayList<>());
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
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(trelloApiEndPoint + host);

        url.queryParam("key", trelloAppKey);
        url.queryParam("token", trelloAppToken);

        for (Map.Entry<String, String> value : params.entrySet()) {
            url.queryParam(value.getKey(), value.getValue());
        }

        return url.build().encode().toUri();
    }
}