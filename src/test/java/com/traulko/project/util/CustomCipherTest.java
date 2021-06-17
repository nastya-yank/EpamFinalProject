package com.traulko.project.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;

import static org.testng.Assert.*;

public class CustomCipherTest {
    @DataProvider(name = "encryptPositiveData")
    public Object[][] createEncryptPositiveData() {
        return new Object[][]{
                {"password", "b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a" +
                        "8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86"},
                {"newPassword", "6f63f637f1346149532158022899bdf424a19c3dc472e21c2068cd324" +
                        "d7263ed521fb1c1335afaad6bf3fd94a24c0371217086295255e7773eb8deb2c7a54e1a"},
                {"123456qwerty", "65c9956e54e10ef9a27ae47195ad47a77970cff9cfd1caaba76b9daf" +
                        "0a0c8a0548509ca7eb0f722e4273d3a730adb5e0a981a0e2002bd7161182d66f3b164e36"}
        };
    }

    @Test(dataProvider = "encryptPositiveData")
    public void encryptPositiveTest(String password, String expected) {
        try {
            String actual = CustomCipher.encrypt(password);
            assertEquals(actual, expected);
        } catch (NoSuchAlgorithmException e) {
            fail("No algorithm", e);
        }
    }

    @DataProvider(name = "encryptNegativeData")
    public Object[][] createEncryptNegativeData() {
        return new Object[][]{
                {"password", "password"},
                {"newPassword", null},
                {"123456qwerty", " "}
        };
    }

    @Test(dataProvider = "encryptNegativeData")
    public void encryptNegativeTest(String password, String expected) {
        try {
            String actual = CustomCipher.encrypt(password);
            assertNotEquals(actual, expected);
        } catch (NoSuchAlgorithmException e) {
            fail("No algorithm", e);
        }
    }
}