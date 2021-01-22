package org.chobit.spring.runner;

import org.chobit.spring.cache.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;


//@Component
public class RedisRunner implements CommandLineRunner {

    @Autowired
    private RedisClient client;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Please enter cmd:");
        try (Scanner scanner = new Scanner(System.in)) {
            OUT:
            while (true) {
                String line = scanner.nextLine();
                if (isBlank(line)) {
                    continue;
                }
                String[] arr = line.split(" ");
                switch (arr[0]) {
                    case "get":
                        System.out.println(client.get(arr[1]));
                        break;
                    case "hget":
                        System.out.println(client.hget(arr[1], arr[2]));
                    case "keys":
                        client.keys(arr[1]).forEach(System.out::println);
                        break;
                    case "exit":
                        break OUT;
                    default:
                        System.out.println("Invalid cmd.");
                }
            }

            System.out.println("Bye!");
        }
    }

    private boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

}
