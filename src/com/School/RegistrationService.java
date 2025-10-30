package com.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistrationService {

    private final List<Student> students = new ArrayList<>();
    private final List<Teacher> teachers = new ArrayList<>();
    private final List<Staff> staffMembers = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();

    private final FileStorageService fileStorageService;

    public RegistrationService(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    public void registerStudent(Student s) { students.add(s); }
    public void registerTeacher(Teacher t) { teachers.add(t); }
    public void registerStaff(Staff s) { staffMembers.add(s); }

    // Keep old API that accepts a Course object
    public void createCourse(Course c) { courses.add(c); }

    // New API: create course by name + capacity
    public Course createCourse(String courseName, int capacity) {
        Course c = new Course(courseName, capacity);
        courses.add(c);
        return c;
    }

    public List<Student> getStudents() { return students; }
    public List<Teacher> getTeachers() { return teachers; }
    public List<Staff> getStaffMembers() { return staffMembers; }
    public List<Course> getCourses() { return courses; }

    public Student findStudentById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Course findCourseById(int id) {
        String idStr = String.valueOf(id);
        Optional<Course> found = courses.stream()
                .filter(c -> String.valueOf(c.getCourseId()).equals(idStr))
                .findFirst();
        return found.orElse(null);
    }

    public Course findCourseById(String id) {
        Optional<Course> found = courses.stream()
                .filter(c -> String.valueOf(c.getCourseId()).equals(id))
                .findFirst();
        return found.orElse(null);
    }

    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        people.addAll(students);
        people.addAll(teachers);
        people.addAll(staffMembers);
        return people;
    }

    // Enroll a student in a course via Course.addStudent
    public boolean enrollStudentInCourse(Student student, Course course) {
        if (student == null || course == null) {
            System.out.println("❌ Enrollment failed: student or course is null.");
            return false;
        }
        boolean success = course.addStudent(student);
        if (!success) {
            System.out.println("ℹ️ Enrollment attempt failed for student ID: " + student.getId() + " in course " + course.getCourseName());
        }
        return success;
    }

    public void saveAllRegistrations() {
        System.out.println("\n--- Saving Registrations ---");
        System.out.println("Students count: " + students.size());
        System.out.println("Teachers count: " + teachers.size());
        System.out.println("Staff count: " + staffMembers.size());
        System.out.println("Courses count: " + courses.size());

        List<Storable> tmp = new ArrayList<>();

        tmp.clear();
        tmp.addAll(students);
        fileStorageService.saveData(tmp, "students.txt");

        tmp.clear();
        tmp.addAll(teachers);
        fileStorageService.saveData(tmp, "teachers.txt");

        tmp.clear();
        tmp.addAll(staffMembers);
        fileStorageService.saveData(tmp, "staff.txt");

        tmp.clear();
        tmp.addAll(courses);
        fileStorageService.saveData(tmp, "courses.txt");
    }
}