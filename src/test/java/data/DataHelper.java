package data;

import data.AuthInfo;
import data.VerificationCode;

public class DataHelper {

private DataHelper() {}

    public static AuthInfo getAuthInfo() {
    return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
    return new VerificationCode("12345");
    }
}
