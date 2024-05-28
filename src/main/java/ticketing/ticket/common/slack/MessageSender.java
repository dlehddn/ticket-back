package ticketing.ticket.common.slack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MessageSender {

    @Value("${slack.webhook.uri}")
    private String SLACK_LOGGER_WEBHOOK_URI;
    private final ObjectMapper objectMapper;

    public void send(String message) {
        WebClient.create(SLACK_LOGGER_WEBHOOK_URI)
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(toJson(message))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }

    private String toJson(String message) {
        try {
            Map<String, String> values = new HashMap<>();
            values.put("text", message);
            return objectMapper.writeValueAsString(values);
        } catch (JsonProcessingException ignored) {}
        return "{\"text\" : \"슬랙으로 보낼 데이터를 제이슨으로 변경하는데 에러가 발생함.\"}";
    }


}
