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

    public void sendUserScore(User user) {
        try{
            amqpTemplate.convertAndSend(
                    "user-score",
                    objectMapper.writeValueAsString(user)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMatchStart(Match match) {
        try {
            amqpTemplate.convertAndSend(
                    "match-queue",
                    objectMapper.writeValueAsString(match)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
