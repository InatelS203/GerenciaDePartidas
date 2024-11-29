package matchManager.cosumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import matchManager.model.match.Match;
import matchManager.model.user.User;
import matchManager.service.MatchService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MatchManagerConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MatchService matchService;

    @RabbitListener(queues = "match-queue")
    public void receiveMatchQueue(String message) {
        try {
            List<User> users = objectMapper.readValue(message, new TypeReference<List<User>>() {});
            matchService.createMatch(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "match-finish")
    public void receiveMatchFinish(String message) {
        try {
            Match match = objectMapper.readValue(message, Match.class);
            matchService.finishMatch(match);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
