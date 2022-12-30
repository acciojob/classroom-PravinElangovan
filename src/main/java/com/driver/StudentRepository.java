package com.driver;

import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.util.*;

@Repository
public class StudentRepository {

    private HashMap<String,Student> studentDB;
    private HashMap<String,Teacher> teacherDB;
    private HashMap<String, List<String>> studentTeacherPairMap ;

    public StudentRepository() {
        this.studentDB = new HashMap<String,Student>();
        this.teacherDB = new HashMap<String,Teacher>();
        this.studentTeacherPairMap=new HashMap<String, List<String>>();
    }

    public void addTeacher(Teacher teacher) {
        teacherDB.put(teacher.getName(),teacher);
    }

    public void addStudent(Student student) {
        studentDB.put(student.getName(), student);
    }

    public void createStudentTeacherPair(String student, String teacher) {
        if(studentDB.containsKey(student) && teacherDB.containsKey(teacher)){
            // create list of string
            List<String> currentStudentsByTeacher =new ArrayList<>();
            if(studentTeacherPairMap.containsKey(teacher)) {

                currentStudentsByTeacher = studentTeacherPairMap.get(teacher);
                currentStudentsByTeacher.add(student);
            }
            studentTeacherPairMap.put(teacher,currentStudentsByTeacher);
        }
    }

    public Student findStudent(String name) {
        Student student=studentDB.get(name);
        return student;
    }

    public Teacher findTeacher(String name) {
        Teacher teacher = teacherDB.get(name);
        return teacher;
    }

    public List<String> getStudentsByTeacher(String teacher) {

        List<String>studentList=new ArrayList<>();
        if(studentTeacherPairMap.containsKey(teacher)){
            studentList = studentTeacherPairMap.get(teacher);
        }
        return studentList;
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentDB.keySet());
    }


    public void deleteTeacher(String teacher) {
        List<String> students=new ArrayList<>();
        //Delete students
        if(studentTeacherPairMap.containsKey(teacher)){
            students=studentTeacherPairMap.get(teacher);
            for(String student: students){
                if(studentDB.containsKey(student)){
                    studentDB.remove(student);
                }
            }
            //Delete the pairs
            studentTeacherPairMap.remove(teacher);
            //Delete the teacher
            if(teacherDB.containsKey(teacher)){
                teacherDB.remove(teacher);
            }
        }
    }

    public void deleAllTeachers() {
        HashSet<String>studentSet= new HashSet<>();
        teacherDB =new HashMap<>();
        for(String teacher: studentTeacherPairMap.keySet()){
            for(String student : studentSet){
                studentSet.add(student);
            }
        }
        for(String student: studentSet ){
            if(studentDB.containsKey(student)) {
                studentDB.remove(student);
            }
        }

        //clearing the pair
        studentTeacherPairMap= new HashMap<>();
    }
}
