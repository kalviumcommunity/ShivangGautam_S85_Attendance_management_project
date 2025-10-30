package com.school;

import java.util.ArrayList;
import java.util.List;

public class Course implements Storable {
    private static int nextCourseCounter = 101;

    private final String courseId;
    private String courseName;
    private int capacity;
    private final List<Student> enrolledStudents;

    // Existing single-arg constructor preserved as default capacity fallback
    public Course(String courseName) {
        this(courseName, 30); // default capacity
    }

    // New constructor that accepts capacity
    public Course(String courseName, int capacity) {
        this.courseId = "C" + nextCourseCounter++;
        this.courseName = courseName;
        this.capacity = Math.max(0, capacity);
        this.enrolledStudents = new ArrayList<>();
    }

    // Getters
    public String getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public int getCapacity() { return capacity; }
    public List<Student> getEnrolledStudents() { return enrolledStudents; }
    public int getNumberOfEnrolledStudents() { return enrolledStudents.size(); }

    // Enrollment - returns true if added
    public boolean addStudent(Student student) {
        if (student == null) return false;
        if (enrolledStudents.contains(student)) {
            System.out.println("⚠️ Student " + student.getName() + " (ID: " + student.getId() + ") already enrolled in " + courseName);
            return false;
        }
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            System.out.println("✅ Enrolled " + student.getName() + " (ID: " + student.getId() + ") in " + courseName);
            return true;
        } else {
            System.out.println("❌ Cannot enroll " + student.getName() + " (ID: " + student.getId() + ") - capacity reached for " + courseName);
            return false;
        }
    }

    public void displayDetails() {
        System.out.println("Course ID: " + courseId + ", Name: " + courseName);
        System.out.println("Capacity: " + capacity + ", Enrolled: " + getNumberOfEnrolledStudents());
    }

    @Override
    public String toDataString() {
        // include capacity for persistence
        return courseId + "," + courseName + "," + capacity;
    }
}
