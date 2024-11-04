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

    public void sendRecommendedMatch(User[] user) throws JsonProcessingException {
        Timestamp timestamp = Timestamp.from(Instant.now());
        Match match = new Match(UUID.randomUUID(), user[0], user[1], user[1].getId(), timestamp, timestamp);

        amqpTemplate.convertAndSend(
                "match-queue",
                objectMapper.writeValueAsString(match)
        );
    }
}
