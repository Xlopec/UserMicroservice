package domain.user;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void updateNickname() {
        Nickname oldNick = new Nickname("oldnick");
        Nickname newNick = new Nickname("another");
        User user = new User(new Email("some@example.com"), oldNick);

        assertEquals(oldNick, user.getNickname());

        user.updateNickname(newNick);

        assertEquals(newNick, user.getNickname());
    }

    @Test
    public void updateAvatar() {
    }
}