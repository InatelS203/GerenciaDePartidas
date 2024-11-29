package matchManager.service;

import matchManager.model.match.Match;
import matchManager.model.user.User;
import matchManager.producer.MatchManagerProducer;
import matchManager.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchManagerProducer matchManagerProducer;

    @Autowired
    private UserService userService;

    public void createMatch(List<User> users) {
        Timestamp timenow = new Timestamp(System.currentTimeMillis());
        Match match = new Match(users.get(0), users.get(1), timenow);

        Match matchWithId = matchRepository.save(match);

        String url = generateLink(matchWithId.getId());
        matchWithId.setLink(url);
        matchRepository.save(matchWithId);

        matchManagerProducer.sendMatchStart(match);
    }

    public void finishMatch(Match match) {
        User winner;
        User loser;

        UUID winner_id = match.getWinner_id();
        if (match.getUser1().getId() == winner_id) {
            winner = match.getUser1();
            loser = match.getUser2();
        } else {
            winner = match.getUser2();
            loser = match.getUser1();
        }

        var user = calculatePointsUser(winner, loser);
        winner = user.get(0);
        loser = user.get(1);

        userService.updateUser(winner);
        userService.updateUser(loser);
    }

    private String generateLink(UUID matchId) {
        return "https://www.seila.com/match/" + matchId;
    }

    private List<User> calculatePointsUser(User winner, User loser) {
        List<User> users = new ArrayList<>();

        Integer newRankWinner = winner.getRank() + 10;
        Integer newRankLoser = loser.getRank() - 10;

        winner.setRank(newRankWinner);
        loser.setRank(newRankLoser);

        users.add(winner);
        users.add(loser);

        return users;
    }
}
