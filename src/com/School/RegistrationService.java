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
    public void createCourse(Course c) { courses.add(c); }

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

    // Overload: find by int (keeps API requested) and by String id
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