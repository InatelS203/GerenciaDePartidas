package matchManager.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import matchManager.model.match.Match;
import matchManager.model.user.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Component
public class MatchRecommenderMockProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendRecommendedMatch(User[] users) throws JsonProcessingException {

        amqpTemplate.convertAndSend(
                "match-queue",
                objectMapper.writeValueAsString(users)
        );
    }
}
