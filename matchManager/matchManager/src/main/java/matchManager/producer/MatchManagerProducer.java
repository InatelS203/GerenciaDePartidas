package matchManager.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import matchManager.model.match.Match;
import matchManager.model.user.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchManagerProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Envio na fila de usuarios
    public void sendUser(User user) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "user-queue",
                objectMapper.writeValueAsString(user)
        );
    }

    // Envio na fila de partidas
    public void sendMatch(Match match) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "match-queue",
                objectMapper.writeValueAsString(match)
        );
    }
}
