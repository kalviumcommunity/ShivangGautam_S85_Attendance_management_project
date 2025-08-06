package com.school;

public class AttendanceRecord {
    private int studentId;
    private int courseId;
    private String status;      

    public AttendanceRecord(int studentId, int courseId, String status) {
        this.studentId = studentId;
        this.courseId = courseId;

        // Normalize and validate status
        if ("Present".equalsIgnoreCase(status)) {
            this.status = "Present";
        } else if ("Absent".equalsIgnoreCase(status)) {
            this.status = "Absent";
        } else {
            this.status = "Invalid";
            System.out.println("Warning: Invalid attendance status provided. Set to 'Invalid'.");
        }
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if ("Present".equalsIgnoreCase(status)) {
            this.status = "Present";
        } else if ("Absent".equalsIgnoreCase(status)) {
            this.status = "Absent";
        } else {
            this.status = "Invalid";
            System.out.println("Warning: Invalid attendance status provided. Set to 'Invalid'.");
        }
    }

    public void displayRecord() {
        System.out.println("Attendance: Student ID " + studentId +
                " in Course ID C" + courseId + " - Status: " + status);
    }
}
