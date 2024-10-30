package matchManager.repository;

import matchManager.model.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatchRepository extends JpaRepository<Match, UUID> {
}