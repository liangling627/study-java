package com.jdk8.stream;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Hank
 * @Date 2019年03月27日
 */
public class PathTest {
    public static void main(String[] args) {
        Path path = Paths.get("work");
        Path root = path.getRoot();
        System.out.println(root.getFileSystem());

    }
}
