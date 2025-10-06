package com.school;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // ✅ Helper method to display any school directory
    public static void displaySchoolDirectory(List<Person> people) {
        System.out.println("\n--- School Directory ---");
        if (people.isEmpty()) {
            System.out.println("No people in the directory.");
            return;
        }
        for (Person person : people) {
            person.displayDetails();
        }
    }

    public static void main(String[] args) {
        System.out.println("--- School Administration & Attendance System ---");

        // --- Setup: People ---
        Student student1 = new Student("Alice Wonderland", "Grade 10");
        Student student2 = new Student("Bob The Builder", "Grade 9");
        Teacher teacher1 = new Teacher("Dr. Emily Carter", "Physics");
        Staff staff1   = new Staff("Mr. John Davis", "Librarian");

        List<Person> schoolPeople = new ArrayList<>();
        schoolPeople.add(student1);
        schoolPeople.add(student2);
        schoolPeople.add(teacher1);
        schoolPeople.add(staff1);

        displaySchoolDirectory(schoolPeople);

        // --- Setup: Courses ---
        Course course1 = new Course("Intro to Quantum Physics");  // ID auto: C101
        Course course2 = new Course("Advanced Algorithms");       // ID auto: C102
        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);

        System.out.println("\n--- Available Courses ---");
        for (Course c : courses) {
            c.displayDetails();
        }

        // --- Attendance Records ---
        List<AttendanceRecord> attendanceLog = new ArrayList<>();
        attendanceLog.add(new AttendanceRecord(student1, course1, "Present"));
        attendanceLog.add(new AttendanceRecord(student2, course1, "Absent"));
        attendanceLog.add(new AttendanceRecord(student1, course2, "Daydreaming")); // invalid status

        System.out.println("\n--- Attendance Log ---");
        for (AttendanceRecord ar : attendanceLog) {
            ar.displayRecord();
        }

        // --- Saving Data ---
        System.out.println("\n--- Saving Data ---");
        FileStorageService storageService = new FileStorageService();
        storageService.saveData(List.of(student1, student2), "students.txt");
        storageService.saveData(courses, "courses.txt");
        storageService.saveData(attendanceLog, "attendance_log.txt");

        System.out.println("\nSession Complete.");
    }
}
