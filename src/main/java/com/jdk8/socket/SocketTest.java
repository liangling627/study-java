package com.jdk8.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Hank
 * @Date 2019年04月10日
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
        try (Socket s = new Socket("time-a.nist.gov", 13);
             Scanner in = new Scanner(s.getInputStream(), "UTF-8")) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        }


    }
}
