package matchManager.cosumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import matchManager.model.match.Match;
import matchManager.model.user.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MatchManagerConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "user-queue")
    public void userReceive(String message) {
        try {
            User user = objectMapper.readValue(message, User.class);
            System.out.println(user.toString());
            // TODO: Lógica do Service
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "match-queue")
    public void matchReceive(String message) {
        try {
            Match match = objectMapper.readValue(message, Match.class);
            System.out.println(match.toString());
            // TODO: Lógica do Service
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
