package matchManager.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import matchManager.model.match.Match;
import matchManager.model.user.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FrontendMockProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedRate = 2000)
    public void queueUserToPlay() throws JsonProcessingException {
        User sampleUser = new User(UUID.randomUUID(), "ZéMock", 100);
        amqpTemplate.convertAndSend(
                "user-queue",
                objectMapper.writeValueAsString(sampleUser)
        );
    }

}
