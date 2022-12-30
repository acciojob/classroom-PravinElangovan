package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @PostMapping("/add_student")
    public void addStudent(Student student){
        studentRepository.addStudent(student);
    }
    @PostMapping("/add_student")
    public void addTeacher(Teacher teacher){
        studentRepository.addTeacher(teacher);
    }

    public void studentTeacherPair(String student, String teacher) {
        studentRepository.createStudentTeacherPair(student,teacher);
    }

    public Student findStudent(String name) {
        return studentRepository.findStudent(name);
    }

    public Teacher findTeacher(String name) {
        return studentRepository.findTeacher(name);
    }

    public List<String> getStudentsByTeacher(String teacher) {
        return studentRepository.getStudentsByTeacher(teacher);
    }

    public List<String> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void deleteTeacherByName(String teacher) {
        studentRepository.deleteTeacherByNameFromDb(teacher);
    }

    public void deleteAllTeachers() {
        studentRepository.deleteAllTeachersFromDb();
    }
}
