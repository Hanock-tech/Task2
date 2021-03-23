package com.csv2jsontask.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Csv2jsonServiceImplements implements Csv2jsonService {

	@Override
	public String dateValidation(String date) {

		String[] dateValue = date.split("/");
		List<String> datevalues = new ArrayList<String>();
		for (String s : dateValue) {
			if (s.length() == 1) {
				String date1 = "0" + s + "/";
				datevalues.add(date1);
			} else if (s.length() == 2) {
				String date2 = s + "/";
				datevalues.add(date2);
			} else if (s.length() > 3) {
				datevalues.add(s);
			}

		}
		String date2 = "";
		for (String date3 : datevalues) {
			date2 = date2 + date3;
		}
		return date2;

	}

	@Override
	public String yearsAndMonthsValidation(String date) {

		String yearsAndMonths = "";
		String[] list = date.split("/");
		int day = Integer.parseInt(list[1]);
		int month = Integer.parseInt(list[0]);
		int year = Integer.parseInt(list[2]);
		LocalDate bday = LocalDate.of(year, month, day);
		LocalDate today = LocalDate.now();
		Period age = Period.between(bday, today);
		int years = age.getYears();
		int months = age.getMonths();
		yearsAndMonths = years + " " + "Yrs" + " " + months + " " + "Months";
		return yearsAndMonths;

	}

	@Override
	public int ageValidation(String date) {

		String[] list = date.split("/");
		int day = Integer.parseInt(list[1]);
		int month = Integer.parseInt(list[0]);
		int year = Integer.parseInt(list[2]);
		LocalDate bday = LocalDate.of(year, month, day);
		LocalDate today = LocalDate.now();
		Period age = Period.between(bday, today);
		int years = age.getYears();

		return years;

	}

	@Override
	public String gradeEvalution(int totalMarks) {

		if (totalMarks >= 950) {
			return "A";
		} else if (totalMarks >= 800 && totalMarks <= 949) {
			return "B";
		} else if (totalMarks >= 650 && totalMarks <= 799) {
			return "c";
		} else if (totalMarks >= 500 && totalMarks <= 649) {
			return "D";
		}
		return "F";
	}

	@Override
	public String fileValidation(String filePath) throws IOException {
		String status = "success";
		String status1 = "failed";
		if (filePath.isEmpty() || filePath.isBlank()) {
			throw new FileNotFoundException();
		}
		File Originalfile = new File(filePath);
		if (Originalfile.exists()) {
			throw new IOException("File already exits.Please try with diffrent path");
		} else {
			Boolean path = Originalfile.mkdir();
			if (path.booleanValue() == true) {
				return status;
			}
		}
		return status1;
	}

}
