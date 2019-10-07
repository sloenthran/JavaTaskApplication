package pl.nogacz.tasks.integration.util;

import java.net.URL;

public class Resources {
    public static String getPath(String fileName) {
        ClassLoader classLoader = Resources.class.getClassLoader();

        URL resource = classLoader.getResource(fileName);

        return resource.getPath();
    }
}
