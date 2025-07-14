package com.school;

    public class Course{
        int CourseId;
        String courseName;

        public void setDetails(int id,String coursename){
            this.CourseId=id;
            this.courseName=coursename;
        }

        public void displayDetails() {
            System.out.println("Course ID: " + this.CourseId + ", Course Name: " + this.courseName);
        }
    }