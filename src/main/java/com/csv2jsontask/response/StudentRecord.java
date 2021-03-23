package com.csv2jsontask.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.csv2jsontask.model.Student;

public class StudentRecord {

	private Integer recordCount;
	private List<Student> studentData = new ArrayList<Student>();

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public List<Student> getStudentData() {
		return studentData;
	}

	public void setStudentData(List<Student> studentData) {
		this.studentData = studentData;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

}
