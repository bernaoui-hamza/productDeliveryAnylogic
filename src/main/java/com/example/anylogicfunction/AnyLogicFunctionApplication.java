package com.example.anylogicfunction;

import com.example.anylogicfunction.Entities.City;
import com.example.anylogicfunction.Repositories.CityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

import static com.example.anylogicfunction.Services.ServiceSomme.*;

@SpringBootApplication
public class AnyLogicFunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnyLogicFunctionApplication.class, args);
    }

@Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
           // String jsonString = "[{\"name\":\"John\",\"age\":30},{\"name\":\"Mary\",\"age\":25}]";
            String jsonString = "[{\"id\":5,\"longitude\":1770.4,\"latitude\":4301.0,\"name\":\"Vienna\"},{\"id\":8,\"longitude\":100.2,\"latitude\":230.0,\"name\":\"Munich\"},{\"id\":3,\"longitude\":9830.0,\"latitude\":10.0,\"name\":\"Brusselle\"}]";

                List<City> cityList = (List<City>) json1ToList(jsonString, City.class);

                for(City c1:cityList){
                    System.out.println(c1.getId()+"--"+c1.getName()+"---"+c1.getLatitude()+"--"+c1.getLongitude());
                }

        };


    }
}
