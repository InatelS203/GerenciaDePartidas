package matchManager.service;

import matchManager.model.user.User;
import matchManager.producer.MatchManagerProducer;
import matchManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchManagerProducer matchManagerProducer;

    public void updateUser(User user) {
        userRepository.save(user);

        matchManagerProducer.sendUserScore(user);
    }
}
