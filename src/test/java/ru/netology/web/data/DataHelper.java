package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        public String login;
        public String password;
    }

    public static AuthInfo getAuthInfoValid() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getAuthInfoInvalid() {
        return new AuthInfo("vasya", "123qwerty");
    }

    @Value
    public static class VerificationCode{
        private String codeForVerification;
    }

    public static VerificationCode getVerificationCode(){
        return new VerificationCode("12345");
    }

    @Value
    @AllArgsConstructor
    public static class CardsInfo{
        public String numberOfCard;
    }

    public static CardsInfo getfirstCardInfo(){
        return new CardsInfo("5559 0000 0000 0001");
    }

    public static CardsInfo getsecondCardInfo(){
        return new CardsInfo("5559 0000 0000 0002");
    }
}
