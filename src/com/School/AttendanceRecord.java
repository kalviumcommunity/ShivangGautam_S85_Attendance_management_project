package com.school;

public class AttendanceRecord implements Storable {
    private Student student;
    private Course course;
    private String status;

    // ✅ Constructor with validation
    public AttendanceRecord(Student student, Course course, String status) {
        if (student == null || course == null) {
            throw new IllegalArgumentException("Student and Course cannot be null.");
        }
        this.student = student;
        this.course = course;

        // Validate status input
        if (status != null && 
           ("Present".equalsIgnoreCase(status) || "Absent".equalsIgnoreCase(status))) {
            this.status = capitalize(status);
        } else {
            this.status = "Invalid";
            System.out.println("⚠ Warning: Invalid attendance status ('" + status + "'). Set to 'Invalid'.");
        }
    }

    // ✅ Getter methods
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public String getStatus() { return status; }

    // ✅ Display nicely formatted record
    public void displayRecord() {
        String studentInfo = (student != null) ? 
            student.getName() + " (ID: " + student.getId() + ")" : "Unknown Student";
        String courseInfo = (course != null) ? 
            course.getCourseName() + " (ID: C" + course.getCourseId() + ")" : "Unknown Course";

        System.out.println("Attendance: " + studentInfo + " in " + courseInfo + " - Status: " + status);
    }

    // ✅ Convert record to savable string
    @Override
    public String toDataString() {
        return student.getId() + "," + course.getCourseId() + "," + status;
    }

    // ✅ Override toString() for easier debugging/logging
    @Override
    public String toString() {
        return "AttendanceRecord{" +
               "student=" + student.getName() + " (ID: " + student.getId() + ")" +
               ", course=" + course.getCourseName() + " (ID: C" + course.getCourseId() + ")" +
               ", status='" + status + '\'' +
               '}';
    }

    // ✅ Utility to capitalize first letter (for uniformity)
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
