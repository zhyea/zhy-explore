package org.chobit.spring.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class MyRunner3 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Please enter your name:");
        try (Scanner scanner = new Scanner(System.in)) {
            // 这里执行会报错，因为MyRunner4会先执行，并在执行结束后将System.in关闭掉
            String name = scanner.nextLine();
            System.out.println("Hello " + name + "!");
            System.out.println("Bye!");
        }
    }

}
