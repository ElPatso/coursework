package ua.lemekh.mailEvent;

import ua.lemekh.model.User;

public interface MailSernder {
    void sendToUsers(User user);
}
