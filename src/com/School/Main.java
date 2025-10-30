package com.school;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- School Administration System ---");

        FileStorageService storageService = new FileStorageService();
        RegistrationService registrationService = new RegistrationService(storageService);

        // --- Register People ---
        Student s1 = new Student("Alice Wonderland", "Grade 10");
        Student s2 = new Student("Bob The Builder", "Grade 9");
        Student s3 = new Student("Charlie Brown", "Grade 10");

        Teacher t1 = new Teacher("Dr. Strange", "Physics");
        Staff st1 = new Staff("Mr. Clean", "Janitor");

        registrationService.registerStudent(s1);
        registrationService.registerStudent(s2);
        registrationService.registerStudent(s3);
        registrationService.registerTeacher(t1);
        registrationService.registerStaff(st1);

        // --- Create Courses with capacities ---
        Course c1 = registrationService.createCourse("Intro to Quantum Physics", 2); // small capacity for demo
        Course c2 = registrationService.createCourse("Advanced Algorithms", 1);

        // --- Enroll Students (one attempt will exceed capacity) ---
        registrationService.enrollStudentInCourse(s1, c1);
        registrationService.enrollStudentInCourse(s2, c1);
        // This will exceed c1 capacity (2) and should fail
        registrationService.enrollStudentInCourse(s3, c1);

        // Enroll into c2 (capacity 1)
        registrationService.enrollStudentInCourse(s1, c2);
        // this will fail
        registrationService.enrollStudentInCourse(s2, c2);

        // --- Display Directory ---
        System.out.println("\n--- School Directory ---");
        for (Person p : registrationService.getAllPeople()) {
            p.displayDetails();
        }

        // --- Display Courses (with capacities & enroll counts) ---
        System.out.println("\n--- Courses ---");
        for (Course c : registrationService.getCourses()) {
            c.displayDetails();
        }

        // --- Save Everything ---
        System.out.println("\n--- Saving All Data ---");
        registrationService.saveAllRegistrations();
        System.out.println("✅ All data saved successfully.");

        System.out.println("\nPart 10 complete. Check courses.txt for capacity and enrolled counts printed to console.");
    }
}