package matchManager.cosumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import matchManager.model.match.Match;
import matchManager.model.user.User;
import matchManager.producer.MatchRecommenderMockProducer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchRecommenderMockConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<User> userQueue = new ArrayList<>();

    @Autowired
    private MatchRecommenderMockProducer matchRecommenderMockProducer;

    @RabbitListener(queues = "user-queue")
    public synchronized void userReceive(String message) {
        try {
            User user = objectMapper.readValue(message, User.class);
            userQueue.add(user);
            System.out.println(user.toString());
            if (userQueue.size() >= 2) {
                matchRecommenderMockProducer.sendRecommendedMatch(userQueue.toArray(new User[2]));
                userQueue.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
