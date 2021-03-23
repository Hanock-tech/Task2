package com.csv2jsontask.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.csv2jsontask.model.Teacher;

public class TeacherRecord {
	private Integer recordCount;
	private List<Teacher> teacherData = new ArrayList<Teacher>();

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public List<Teacher> getTeacherData() {
		return teacherData;
	}

	public void setTeacherData(List<Teacher> teacherData) {
		this.teacherData = teacherData;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

}
