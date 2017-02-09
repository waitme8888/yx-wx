package com.yx.wx.platform.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class Test {

    interface Formula {
        double calculate(int a);

        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }

    static class Person {
        public static Person newInstants(String name) {
            return new Person(name);
        }

        public Person() {

        }

        public Person(String name) {

        }
    }

    interface PersonFactory {
        //        Person create();
        Person create(String name);
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, (a, b) -> a.compareTo(b));

        Formula a = s -> Long.valueOf(s);
        System.out.println(a.calculate(3));

        Formula a1 = Long::valueOf;
        System.out.println(a1.calculate(4));

        PersonFactory factory = Person::new;
        PersonFactory factory1 = Person::newInstants;
        factory.create("");
        factory1.create("");

        Predicate<String> predicate = (s) -> s.length() > 0;
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, Long> backToString = toInteger.andThen(Long::valueOf);
        Long aaaa = backToString.apply("123");

        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p);
        greeter.accept(new Person("Luke"));

        List<Integer> nums = Arrays.asList(1, null, 3, 4, null, 6);
        nums.stream().filter(num -> num != null).count();

        nums.stream().skip(2).forEach(System.out::println);
    }
}
