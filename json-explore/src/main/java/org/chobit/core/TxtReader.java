package org.chobit.core;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author robin
 */
public class TxtReader {

    public static String read(String path) {
        StringBuilder builder = new StringBuilder();
        try (FileReader fr = new FileReader(path);
             BufferedReader br = new BufferedReader(fr)) {
            String tmp = br.readLine();
            for (; null != tmp; tmp = br.readLine()) {
                builder.append(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

}
