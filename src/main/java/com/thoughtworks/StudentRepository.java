package com.thoughtworks;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

  public void save(List<Student> students) {
    students.forEach(this::save);
  }

  public void save(Student student) {
    // TODO:
    Connection conn = DbUtil.getConnection();
    String sql = "INSERT INTO student_info VALUES(?,?,?,?,?,?)";
    try {
      PreparedStatement ptmt = conn.prepareStatement(sql);
      ptmt.setString(1, student.getId());
      ptmt.setString(2, student.getName());
      ptmt.setString(3, student.getGender());
      ptmt.setInt(4, student.getAdmissionYear());
      ptmt.setDate(5, new Date(student.getBirthday().getTime()));
      ptmt.setString(6, student.getClassId());
      ptmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }


  }

  public List<Student> query() {
    // TODO:
    Connection conn = DbUtil.getConnection();
    List<Student> students = new ArrayList<>();
    String sql = "SELECT id,name,gender,admission,birthday,class FROM student_info";
    try {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      Student student = null;
      while (rs.next()) {
        student = new Student();
        student.setId(rs.getString("id"));
        student.setName(rs.getString("name"));
        student.setGender(rs.getString("gender"));
        student.setAdmissionYear(rs.getInt("admission"));
        student.setBirthday(rs.getDate("birthday"));
        student.setClassId(rs.getString("class"));
        students.add(student);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return students;
  }

  public List<Student> queryByClassId(String classId) {
    // TODO:
    List<Student> students = new ArrayList<>();
    Connection conn = DbUtil.getConnection();
    String sql = "SELECT id,name,gender,admission,birthday,class FROM student_info WHERE class = ? ORDER BY id DESC";
    try {
      PreparedStatement ptmt = conn.prepareStatement(sql);
      ptmt.setString(1,classId);
      ResultSet rs = ptmt.executeQuery();
      Student student = null;
      while (rs.next()) {
        student = new Student();
        student.setId(rs.getString("id"));
        student.setName(rs.getString("name"));
        student.setGender(rs.getString("gender"));
        student.setAdmissionYear(rs.getInt("admission"));
        student.setBirthday(rs.getDate("birthday"));
        student.setClassId(rs.getString("class"));
        students.add(student);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return students;
  }

  public void update(String id, Student student) {
    // TODO:
    Connection conn = DbUtil.getConnection();
    String sql = "UPDATE student_info SET id = ?, name = ?, gender = ?, admission = ?,  birthday = ?, class = ? WHERE id = ?";
    try {
      PreparedStatement ptmt = conn.prepareStatement(sql);
      ptmt.setString(1, student.getId());
      ptmt.setString(2, student.getName());
      ptmt.setString(3, student.getGender());
      ptmt.setInt(4, student.getAdmissionYear());
      ptmt.setDate(5, new Date(student.getBirthday().getTime()));
      ptmt.setString(6, student.getClassId());
      ptmt.setString(7,id);
      ptmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(String id) {
    // TODO:
    Connection conn = DbUtil.getConnection();
    String sql = "DELETE FROM student_info WHERE id = ?";
    try {
      PreparedStatement ptmt = conn.prepareStatement(sql);
      ptmt.setString(1,id);
      ptmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
