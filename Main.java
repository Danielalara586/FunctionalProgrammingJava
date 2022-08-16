import Interfaces.IAddition;
import Interfaces.ISalute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Lambda function with functional (simple expression)
        ISalute salute = user -> System.out.printf("Hello %s!\n", user);
        salute.salute("Dani");

        // Lambda function with functional (multiple expressions)
        ISalute salute2 = name -> {
            if(name.equals("Cody")) {
                System.out.printf("Hi %s! You have the same name as CodigoFacilito's mascot\n", name);
            }
            else {
                System.out.printf("Hi %s!\n", name);
            }
        };

        salute2.salute("Cody");
        salute2.salute("Ponce");

        // Lambda function with return value
        IAddition addition = (num1, num2) -> num1 + num2;
        int result = addition.addition(10,20);
        System.out.printf("Result: %d\n", result);

        // API STREAM
        // Filter
        List<User> users = new ArrayList<>();
        users.add(new User("Daniela", 22));
        users.add(new User("Isabella", 19));
        users.add(new User("Natalia", 7));
        users.add(new User("Diego", 18));
        users.add(new User("Ricardo", 14));
        users.add(new User("Carlos", 26));

        int elements = (int)users.stream()
                                .filter(user -> user.getAge() > 18)
                                .filter(user -> user.getName().startsWith("D"))
                                .count();
        System.out.printf("%d people are older than 18 and their name starts with 'D'.\n", elements);

        // Create a new stream
        // Collection
        List<User> newUsers = new ArrayList<>();
        Stream<User> stream = users.stream();

        // Stream
        Stream<User> filter = stream.filter(user -> user.getName().startsWith("D"));

        // Array
        int[] numbers = {1,2,3,4,5};
        Stream<int[]> sNumbers = Stream.of(numbers);

        // Sequence
        Stream<String> names = Stream.of("Cody1", "Cody2", "Cody3");

        // Process Methods
        Stream<User> s = users.stream();
        Stream<User> s2 = users.stream();

        // Example: Filter -> Abstraction -> New Stream
        System.out.println(s.filter(user -> user.getAge() > 15));

        // Internal Iteration Methods
        // Example: Count -> Abstraction -> Value
        System.out.println(s2.count());

        // Stream to List
        List<User> userList = users.stream()
                .filter(user -> user.getAge() > 15)
                .collect(Collectors.toList());
        System.out.println(userList);

        // Map Method
        List<Integer> numbersList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbersList.add(i);
        }

        List<Integer> square = numbersList.stream()
                .map(num -> num * num)


                .collect(Collectors.toList());
        System.out.println(square);

        // Flatmap
        List<String> names1 = Arrays.asList("Cody 1", "Cody 2");
        List<String> names2 = Arrays.asList("Cody 3", "Cody 4");
        List<String> names3 = Arrays.asList("Cody 5", "Cody 6");

        List<String> allNames =  Stream.of(names1, names2, names3)
                .flatMap(name -> name.stream())
                .collect(Collectors.toList());

        System.out.println(allNames);

        // Foreach
        List<Integer> numbersList2 = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbersList2.add(i);
        }
        numbersList2.stream().forEach(num -> {
            int numSquare = num * num;
            System.out.println(numSquare);
        });

        //Any Match
        List<Integer> grades = new ArrayList<>();
        grades.add(10);
        grades.add(10);
        grades.add(9);
        grades.add(6);
        grades.add(6);
        grades.add(5);
        grades.add(7);
        grades.add(8);
        grades.add(9);
        grades.add(10);

        boolean fail = grades.stream().anyMatch(grade -> grade < 6);
        System.out.println(fail);

        // Find First & Or Else
        List<User> users2 = new ArrayList<>();
        users2.add(new User("Rafael", 25));
        users2.add(new User("Daniela", 22));
        users2.add(new User("Tania", 25));
        users2.add(new User("Luisa", 24));
        users2.add(new User("Raul", 33));
        users2.add(new User("Carlos", 25));

        User userDefault = new User("No name", 30);

        User user = users2.stream()
                .filter(u -> u.getAge() == 25)
                .findFirst()
                .orElse(userDefault);

        System.out.println(user);

        // sum -> intStream -> mapToInt
        List<Integer> numbersList3 = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbersList3.add(i);
        }

        Long quantity = numbersList3.stream().filter(n -> n % 2 == 0).count();
        int sum = numbersList3.stream().mapToInt(n -> n).sum();
        System.out.println(sum);

        // Average
        double average = numbersList3.stream().mapToInt(n -> n).average().orElse(0);
        System.out.println(average);

        // Min
        int minNum = numbersList3.stream().mapToInt(n -> n).min().getAsInt();
        System.out.println(minNum);

        // Max
        int maxNum = numbersList3.stream().mapToInt(n -> n).max().getAsInt();
        System.out.println(maxNum);

        // Reduce
        int resultSum = Stream.of(1,2,3,4,5,6,7,8,9,10).reduce(0, (counter, element) -> counter+element);
        System.out.println(resultSum);

        // Exercise
        String languages = Stream.of("Java", "C", "Python", "Ruby")
                .reduce("", (ac, lan) -> ac + " | " + lan).substring(3);
        System.out.println(languages);

        // Unique elements
        List<String> nameList = Arrays.asList("Cody 1", "Cody 2", "Cody 1", "Cody 3");
        nameList.stream().distinct().forEach(el -> System.out.println(el));

        // Sorting pt. 1
        List<Integer> sortingNum = Arrays.asList(1, 4, 6, 8, 9, 4, 6, 7, 10, 1, 3, 2, 3, 3, 5);
        List<Integer> sorted = sortingNum.stream() // .sorted() ascending order
                .sorted(Comparator.reverseOrder()) // descending order
                .collect(Collectors.toList());

        System.out.println(sorted);

        // Sorting pt. 2
        List<Book> books = new ArrayList<>();
        books.add(new Book("Lord of the Rings", 150));
        books.add(new Book("The Hobbit", 500));
        books.add(new Book("The Hunger Games", 200));
        books.add(new Book("The Little Prince", 140));

        // Compare
        Comparator<Book> comparator;
        if (books.stream().count() > 3)
            comparator = Comparator.comparing(book -> book.getCopies());
        else
            comparator = Comparator.comparing(book -> book.getTitle());

        books.stream().sorted(comparator.reversed())
                .limit(3)
                .skip(1) // How many elements we want to skip
                .forEach(book -> System.out.println(book.getTitle()));

        /* Reference Methods
        * Static
        * Of Instances
        * Of Instance Arbitrarily
        * Constructor
        */

        // Static
        List<Integer> staticList = Arrays.asList(1, 2, 3, 4, 5);
        staticList.stream().map(Main::toCube)
                .forEach(System.out::println);

        // Of Instance
        List<Integer> instanceList = Arrays.asList(2, 4, 6, 8, 10);
        Calculator calculator = new Calculator();
        instanceList.stream().map(calculator::toCubeCalc)
                .forEach(System.out::println);

        // Of Instance Arbitrarily
        List<User> arbList = new ArrayList<>();
        arbList.add(new User("Daniela", 22));
        arbList.add(new User("Eduardo", 25));
        arbList.add(new User("Uriel", 20));
        arbList.add(new User("Luis", 19));
        arbList.add(new User("Paola", 21));

        arbList.stream().map(User::getName)
                .forEach(System.out::println);

        // Constructor
        List<User> constructList = new ArrayList<>();
        IUser iUser = User::new;
        User user1 = iUser.create("Codi 1", 1);
        User user2 = iUser.create("Codi 2", 2);
        User user3 = iUser.create("Codi 3", 3);
        User user4 = iUser.create("Codi 4", 4);

        constructList.add(user1);
        constructList.add(user2);
        constructList.add(user3);
        constructList.add(user4);

        constructList.stream().map(User::getName)
                .forEach(System.out::println);

    }

    public static int toCube(double num) {
        return (int)Math.pow(num, 3);
    }
}
