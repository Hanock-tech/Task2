package com.csv2jsontask.response;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ResponseData {

	private TeacherRecord teacherRecord;
	private StudentRecord studentRecord;

	public TeacherRecord getTeacherRecord() {
		return teacherRecord;
	}

	public void setTeacherRecord(TeacherRecord teacherRecord) {
		this.teacherRecord = teacherRecord;
	}

	public StudentRecord getStudentRecord() {
		return studentRecord;
	}

	public void setStudentRecord(StudentRecord studentRecord) {
		this.studentRecord = studentRecord;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
}
