package pl.mplywacz.weatherapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//moze sie przydac: https://stackoverflow.com/questions/23499721/how-to-parse-json-array-value-using-json-node

//todo refactor app to store quartz jobs in db so that when re running app jobs continuing work,
// (in that case you need to register only one instance of location in db )

@SpringBootApplication
public class WeatherapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherapiApplication.class, args);
    }

}
