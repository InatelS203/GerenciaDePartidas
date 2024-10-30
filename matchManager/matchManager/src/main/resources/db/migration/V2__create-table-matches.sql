CREATE TABLE matches (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    user1_id UUID,
    user2_id UUID,
    winner UUID,
    start_timestamp TIMESTAMP,
    end_timestamp TIMESTAMP,

    FOREIGN KEY (user1_id) REFERENCES users(id) ON DELETE SET NULL,
    FOREIGN KEY (user2_id) REFERENCES users(id) ON DELETE SET NULL
);