package matchManager.model.match;

import jakarta.persistence.*;
import lombok.*;
import matchManager.model.user.User;

import java.sql.Timestamp;
import java.util.UUID;

@Table(name = "matches")
@Entity(name = "match")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;

    private UUID winner_id;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;

    private  String link;

    public Match(User user1, User user2, Timestamp startTimestamp) {
        this.user1 = user1;
        this.user2 = user2;
        this.startTimestamp = startTimestamp;
    }
}
