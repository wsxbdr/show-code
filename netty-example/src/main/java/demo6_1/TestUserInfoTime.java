package demo6_1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestUserInfoTime {

    public static void main(String[] args) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.buildUserID(100).buildUserName("welcom to netty");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(userInfo);
        os.flush();
        os.close();
        byte[] bytes = bos.toByteArray();
        System.out.println("jdk serializable length is: " + bytes.length);
        bos.close();
        System.out.println("------------------------------------------------");
        System.out.println("the byte array serializabla length is: " + userInfo.codeC().length);
    }
}
