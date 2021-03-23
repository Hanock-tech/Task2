package com.csv2jsontask;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.csv2jsontask.controller.Csv2Json;

@SpringBootApplication
public class Csv2JsonTaskApplication {

	public static void main(String[] args) throws IOException, ParseException {

		SpringApplication.run(Csv2JsonTaskApplication.class, args);
		Csv2Json csv2Json = new Csv2Json();
		csv2Json.readingCsvFile();

	}
}