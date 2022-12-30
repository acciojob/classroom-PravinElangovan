package com.driver;

import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.util.*;

@Repository
public class StudentRepository {

    private static HashMap<String,Student> studentDB;
    private static HashMap<String,Teacher> teacherDB;
    private static HashMap<String, List<String>> studentTeacherPairMap ;

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
//        if(studentDB.containsKey(student) && teacherDB.containsKey(teacher)){
//            // create list of string
//            List<String> currentStudentsByTeacher =new ArrayList<>();
//            if(studentTeacherPairMap.containsKey(teacher))
//
//                currentStudentsByTeacher = studentTeacherPairMap.get(teacher);
//                currentStudentsByTeacher.add(student);
//
//            studentTeacherPairMap.put(teacher,currentStudentsByTeacher);
//        }
        List<String> studentList = studentTeacherPairMap.getOrDefault(teacher,new ArrayList<>());
        studentList.add(student);
        studentTeacherPairMap.put(teacher,studentList);
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
        List<String>studentList =new ArrayList<>();
        for(String student :studentDB.keySet()){
            studentList.add(student);
        }
        return studentList;
    }


    public static void deleteTeacherByNameFromDb(String teacherName){
        List<String> students=new ArrayList<>();
        if(studentTeacherPairMap.containsKey(teacherName)) {
            students=studentTeacherPairMap.get(teacherName);

            for(String student: students){
                if(studentDB.containsKey(student)){
                    studentDB.remove(student);
                }
            }
            studentTeacherPairMap.remove(teacherName);
        }
        if(teacherDB.containsKey(teacherName)){
            teacherDB.remove(teacherName);
        }
    }


    //Delete all teachers from database
    public static void deleteAllTeachersFromDb(){

        teacherDB=new HashMap<>();
        for(String teacher: studentTeacherPairMap.keySet()){
            List<String> students=new ArrayList<>();
            for(String studentName: studentTeacherPairMap.get(teacher)) {
                students.add(studentName);
            }
            for(String name: students){
                if(studentDB.containsKey(name)){
                    studentDB.remove(name);
                }
            }
        }
        studentTeacherPairMap=new HashMap<>();
    }
}
