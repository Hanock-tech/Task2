package com.csv2jsontask.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public interface Csv2jsonService {

	String dateValidation(String date);

	String yearsAndMonthsValidation(String date);

	String gradeEvalution(int totalMarks);

	String fileValidation(String filePath) throws FileNotFoundException, IOException;

	int ageValidation(String date);
}
