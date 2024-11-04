package matchManager.cosumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import matchManager.model.match.Match;
import matchManager.model.user.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FrontendMockConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "match-queue")
    public void matchReceive(String message) {
        try {
            Match match = objectMapper.readValue(message, Match.class);
            System.out.println(match.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
