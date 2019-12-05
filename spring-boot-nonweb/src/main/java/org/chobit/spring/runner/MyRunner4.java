package org.chobit.spring.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Order(0)
@Component
public class MyRunner4 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Please enter your age:");
        try (Scanner scanner = new Scanner(System.in)) {
            String age = scanner.nextLine();
            System.out.println("Ahhhh! you r already " + age + " year old!");
            System.out.println("Bye!");
        }
    }

}
