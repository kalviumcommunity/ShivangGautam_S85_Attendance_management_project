package com.school;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n--- School Attendance System ---");

        // Create and set Student details
        Student[] students = new Student[2];
        students[0] = new Student();
        students[0].setDetails(101, "Alice Wonderland");
        students[1] = new Student();
        students[1].setDetails(102, "Bob The Builder");

        // Create and set Course details
        Course[] courses = new Course[2];
        courses[0] = new Course();
        courses[0].setDetails(5101, "Intro to Programming");
        courses[1] = new Course();
        courses[1].setDetails(4202, "Linear Algebra");

        // Display registered students
        System.out.println("\nRegistered Students:");
        for (Student student : students) {
            if (student != null)
                student.displayDetails(0, null);
        }

        // Display available courses
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            if (course != null)
                course.displayDetails();
        }

        System.out.println("\nSession 2: Core Domain Modelling Complete.");
    }
}