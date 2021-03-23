package com.csv2jsontask.controller;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csv2jsontask.exception.IRErrorCode;
import com.csv2jsontask.exception.InvalidRequestException;
import com.csv2jsontask.model.Student;
import com.csv2jsontask.model.Teacher;
import com.csv2jsontask.response.Response;
import com.csv2jsontask.response.StudentRecord;
import com.csv2jsontask.response.TeacherRecord;
import com.csv2jsontask.service.Csv2jsonService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@RestController

public class SearchDataController {
	@Autowired
	private Csv2jsonService csv2jsonService;

	final String gender1 = "male";
	final String gender2 = "female";
	int studentCount = 1;
	int teacherCount = 1;

	@GetMapping("api/searchData")

	public ResponseEntity<Response> searchData(@RequestParam String category, @RequestParam String age,
			@RequestParam String name) {

		requestValidation(category, age, name);

		Response response = new Response();

		if (category.contentEquals("student")) {

			StudentRecord studentRecord = studentData(category, age, name);

			if (studentRecord.getStudentData().isEmpty()) {

				throw new InvalidRequestException(IRErrorCode.INVAIlD_REQUEST.getErrorCode(),
						IRErrorCode.ERROE_MESSAGE.getErrorCode());
			}

			response.setData(studentRecord);

		} else {

			TeacherRecord teacherRecord = teacherData(category, age, name);
			if (teacherRecord.getTeacherData().isEmpty()) {

				throw new InvalidRequestException(IRErrorCode.INVAIlD_REQUEST.getErrorCode(),
						IRErrorCode.ERROE_MESSAGE.getErrorCode());
			}
			response.setData(teacherRecord);

		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	private TeacherRecord teacherData(String category, String age, String name) {
		List<Teacher> teacherList = new ArrayList<Teacher>();
		try {
			File file = ResourceUtils.getFile("classpath:master_Data/master-data2.csv");

			FileReader filereader = new FileReader(file);
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			List<String[]> fileData = csvReader.readAll();

			for (String[] row : fileData) {

				if (row[1].contentEquals(category))

				{
					Teacher teacherData = new Teacher();

					String dateValue = row[5];
					String ageData = csv2jsonService.yearsAndMonthsValidation(dateValue);
					String[] ageValues = ageData.split(" ");
					String ageValue = ageValues[0];
					int teacherage = Integer.parseInt(ageValue);
					int ageParse = Integer.parseInt(age);

					if (teacherage <= ageParse && (name.equalsIgnoreCase(row[2]) || name.equalsIgnoreCase(row[3]))) {

						teacherData.setId(teacherCount++);
						teacherData.setFullName(row[2] + " " + row[3]);
						if (row[4].contentEquals("m")) {
							teacherData.setGender(gender1);
						} else {
							teacherData.setGender(gender2);
						}
						teacherData.setAadhar(row[16]);
						teacherData.setCity(row[15]);
						teacherData.setContact(row[17]);
						String value = row[5];
						String date = csv2jsonService.dateValidation(row[7]);
						teacherData.setDob(date);
						String teacherAge = csv2jsonService.yearsAndMonthsValidation(value);
						teacherData.setAge(teacherAge);
						teacherData.setEmpNo(row[13]);
						teacherData.setClassTeacherOf(row[11]);
						teacherData.setPerviousSchool(row[6]);
						teacherData.setPost(row[6]);
						teacherData.setSalary(row[10]);
						String dateofJoinValue = row[7];
						String dateofJoining = csv2jsonService.dateValidation(dateofJoinValue);
						teacherData.setDateOfJoining(dateofJoining);
						String servicePeriod = csv2jsonService.dateValidation(dateValue);
						teacherData.setServicePeriod(servicePeriod);
						String subjectTeacher = row[19];
						String[] teacherSubjects = subjectTeacher.split("-");
						List<String> listOfSubjects = new ArrayList<>();
						for (String str : teacherSubjects) {
							listOfSubjects.add(str);
						}
						teacherData.setSubjectTeachers(listOfSubjects);
						teacherList.add(teacherData);

					}

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		TeacherRecord teacherRecord = new TeacherRecord();
		teacherRecord.setRecordCount(teacherList.size());
		teacherRecord.setTeacherData(teacherList);
		studentCount = 1;
		teacherCount = 1;

		return teacherRecord;

	}

	private StudentRecord studentData(String category, String age, String name) {
		List<Student> studentList = new ArrayList<Student>();
		try {
			File file = ResourceUtils.getFile("classpath:master_Data/master-data2.csv");

			FileReader filereader = new FileReader(file);
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			List<String[]> fileData = csvReader.readAll();

			for (String[] row : fileData) {
				Student studentData = new Student();

				if (row[1].contentEquals(category)) {
					String dateValue = row[5];
					int ageData = csv2jsonService.ageValidation(dateValue);
					int agevalue = Integer.parseInt(age);
					if (ageData <= agevalue && (name.equalsIgnoreCase(row[2]) || name.equalsIgnoreCase(row[3]))) {

						studentData.setId(studentCount++);
						studentData.setFullName(row[2] + " " + row[3]);
						if (row[4].contentEquals("m")) {
							studentData.setGender(gender1);
						} else {
							studentData.setGender(gender2);
						}

						String date = csv2jsonService.dateValidation(row[5]);

						studentData.setDob(date);
						studentData.setAadhar(row[16]);
						String dateValues = row[5];

						studentData.setAge(csv2jsonService.yearsAndMonthsValidation(dateValues));

						studentData.setCity(row[15]);

						studentData.setContact(row[17]);

						int rollNumber = Integer.parseInt(row[12]);
						studentData.setRollNumber(rollNumber);
						studentData.setStandard(row[8]);

						int totalMarks = Integer.parseInt(row[14]);
						studentData.setTotalMarks(totalMarks);

						studentData.setGrade(csv2jsonService.gradeEvalution(totalMarks));
						int secPercentage = Integer.parseInt(row[21]);
						studentData.setSecPercentage(secPercentage);
						studentData.setHsStream(row[20]);
						studentList.add(studentData);
					}

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		StudentRecord studentRecord = new StudentRecord();
		studentRecord.setRecordCount(studentList.size());
		studentRecord.setStudentData(studentList);
		studentCount = 1;
		teacherCount = 1;
		return studentRecord;
	}

	private void requestValidation(String category, String age, String name) {

		try {

			Integer.parseInt(age);

		} catch (NumberFormatException ex) {
			throw new InvalidRequestException(IRErrorCode.AGE_INVALID.getErrorCode(),
					IRErrorCode.ERROE_MESSAGE.getErrorCode());
		}

		if (category.isEmpty() && category.isBlank()) {

			throw new InvalidRequestException(IRErrorCode.CATEGORY_EMPTY.getErrorCode(),
					IRErrorCode.ERROE_MESSAGE.getErrorCode());
		}
		if (name.isEmpty()) {
			throw new InvalidRequestException(IRErrorCode.NAME_EMPTY.getErrorCode(),
					IRErrorCode.ERROE_MESSAGE.getErrorCode());
		}

	}

}
