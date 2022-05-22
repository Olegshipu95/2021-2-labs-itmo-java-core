package clientServer;

import lombok.Getter;
import lombok.Setter;

public class UsersLogin {
    @Getter
    @Setter
    private static String name;//нет защиты при отправке на сервер
}
