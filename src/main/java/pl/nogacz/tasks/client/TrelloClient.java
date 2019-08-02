package pl.nogacz.tasks.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.nogacz.tasks.domain.TrelloBoardDto;

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

        params.put("key", trelloAppKey);
        params.put("token", trelloAppToken);
        params.put("fields", "name,id");

        URI url = generateUrl(trelloApiEndPoint + "/members/" + trelloUser + "/boards", params);

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        return  Optional.ofNullable(boardsResponse)
                .map(response -> Arrays.asList(response))
                .orElse(new ArrayList<>());
    }

    private URI generateUrl(String host, HashMap<String, String> params) {
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(host);

        for (Map.Entry<String, String> value : params.entrySet()) {
            url.queryParam(value.getKey(), value.getValue());
        }

        return url.build().encode().toUri();
    }
}