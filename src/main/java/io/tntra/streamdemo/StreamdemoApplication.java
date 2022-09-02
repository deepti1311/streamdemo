package io.tntra.streamdemo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ENUM.pkg.Gender;
import Personpkg.Person;

@SpringBootApplication
public class StreamdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamdemoApplication.class, args);
     

		List<Person> people= getPeople();

		//imperative approch

		List<Person> female= new ArrayList<>();
		for (Person person : people) {

			if(person.getGender().equals(Gender.FEMALE)){
				female.add(person);
			}
			
		}

		// female.forEach(System.out:: println);





		//Declarative approch


		//Filter

		List<Person> females=people.stream()
		  .filter(person-> person.getGender().equals(Gender.MALE))
		  .collect(Collectors.toList());

		//   females.forEach(System.out::println);


		//sort
		List<Person> sorted=people.stream()
		.sorted(Comparator.comparing(Person::getAge)
		.thenComparing(Person::getGender).reversed())
		.collect(Collectors.toList());

		// sorted.forEach(System.out::println);

		//all match
        boolean allmatch=people.stream()
		     .allMatch(person->person.getAge()<90);

		// System.out.println(allmatch);


		//any match

		boolean anymatch=people.stream()
		 .anyMatch(Person->Person.getAge()>90);

		//  System.out.println(anymatch);


		//none match
		boolean nonematch=people.stream()
		  .noneMatch(person->person.getName().equals("paras"));

		//   System.out.println(nonematch);

		//max
		people.stream()
		.max(Comparator.comparing(Person::getAge));
		// .ifPresent(System.out::println);

		//min
		people.stream()
		.min(Comparator.comparing(Person::getAge));
		// .ifPresent(System.out::println);


		//group
		Map<Gender, List<Person>> groupby= people.stream()
		             .collect(Collectors.groupingBy(Person::getGender));



	   groupby.forEach((gender,people1)->{
		System.out.println(gender);
		people1.forEach(System.out::println);
		System.out.println();
	   });


	   Optional<String> oldestFemaleAge =people.stream()
	    .filter(person-> person.getGender().equals(Gender.MALE))
		.max(Comparator.comparing(Person::getAge))
		.map(Person::getName);


		oldestFemaleAge.ifPresent(System.out::println);



		
		





	}

	private static List<Person> getPeople() {
		return List.of(
			new Person("Deepti",22,Gender.FEMALE),
			new Person("paras",21, Gender.MALE),
			new Person("mansi",21,Gender.FEMALE),
			new Person("tiki", 21,Gender.FEMALE),
			new Person("dsjv", 58, Gender.MALE),
			new Person("skdj", 87, Gender.MALE),
			new Person("euwf", 43, Gender.FEMALE)

		);
	}

}
