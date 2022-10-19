package org.itstep;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Java. Lesson027. Task01
 * Nested local and anonymous classes
 * Semenyuk Alexander
 * Date 19.10.2022
 * Завдання
 * Вдосконалити попереднье домашнє завдання з інтерфейсами
 * https://github.com/OOP-Java-DTU-Step-2020/practice-interface
 * за рахунок застосування анонімних класів замість конкретних класів команд.
 * Для цього необхідно в методі main() створити масив команд і реалізувати всі команди у вигляді анонімних класів.
 * Код повинен бути адаптивним щоб легко було додати нову команду.
 * Дивись приклад з заняття.
 */
public class Task01 {
    public static void main(String[] args) {

        abstract class MathOperation {
            String op;
            abstract int operation(int a, int b);
        }

        MathOperation[] operations = {
                new MathOperation() {
                    {
                        op = "+";
                    }
                    @Override
                    int operation(int a, int b) {
                        return a + b;
                    }
                },

                new MathOperation() {
                    {
                        op = "-";
                    }
                    @Override
                    int operation(int a, int b) {
                        return a - b;
                    }
                },

                new MathOperation() {
                    {
                        op = "*";
                    }
                    @Override
                    int operation(int a, int b) {
                        return a * b;
                    }
                },
        };


        Scanner scanner = new Scanner(System.in);
        Pattern mathPattern = Pattern.compile("(?<a>\\d+)\\s*(?<op>[*+-])\\s*(?<b>\\d+)\\s*");

        while (true) {
            System.out.print("Enter operation: ");
            String line = scanner.nextLine();
            Matcher matcher = mathPattern.matcher(line);

            if (matcher.find()) {
                int a = Integer.parseInt(matcher.group("a"));
                String op = matcher.group("op");
                int b = Integer.parseInt(matcher.group("b"));
                for (MathOperation mathOperation : MathOperation.values()) {
                    if (mathOperation.op.equals(op)) {
                        int result = mathOperation.operation(a, b);
                        System.out.println(" = " + result);
                        break;
                    }
                }
            } else {
                System.err.println("It is wrong format");
            }
        }
    }
}
