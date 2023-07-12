package com.mail.inputstream.test;

import org.junit.jupiter.api.Test;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.util.SharedFileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class InputStreamTest {

    @Test
    void test() throws Exception {
        String emlName = "multiple_cid.eml";
        File emlFile = Paths.get("src", "test", "resources", "mails", emlName).toFile();
        InputStream sharedFileInputStream = new SharedFileInputStream(emlFile);

        MimeMessage mimeMessage1 = new MimeMessage(getMailSession(), sharedFileInputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mimeMessage1.writeTo(byteArrayOutputStream);



        File emlFile2 = Paths.get("src", "test", "resources", "mails", emlName).toFile();
        InputStream sharedFileInputStream2 = new SharedFileInputStream(emlFile2);

        MimeMessage mimeMessage2 = new MimeMessage(getMailSession(), sharedFileInputStream2);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        mimeMessage2.writeTo(byteArrayOutputStream2);
    }


    static Session getMailSession() {
        return Session.getDefaultInstance(new Properties());
    }

    static MimeMessage getMimeMessage(String fileName) throws Exception {
        File emlFile = Paths.get("src", "test", "resources", "mails", fileName).toFile();
        InputStream sharedFileInputStream = new SharedFileInputStream(emlFile);

        return new MimeMessage(getMailSession(), sharedFileInputStream);
    }

}
