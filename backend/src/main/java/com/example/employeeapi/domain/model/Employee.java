package com.example.employeeapi.domain.model;

import java.time.LocalDateTime;

public class Employee {
    private Long id;
    private String firstName;
    private String middleName;
    private String paternalLastName;
    private String maternalLastName;
    private Integer age;
    private String gender;
    private String birthDate;
    private String jobTitle;
    private LocalDateTime hireDate;
    private Boolean active;

    public Employee() {}

    public Employee(Long id, String firstName, String middleName, String paternalLastName,
                    String maternalLastName, Integer age, String gender, String birthDate,
                    String jobTitle, LocalDateTime hireDate, Boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.paternalLastName = paternalLastName;
        this.maternalLastName = maternalLastName;
        this.age = age;
        this.gender = gender;
        this.birthDate = birthDate;
        this.jobTitle = jobTitle;
        this.hireDate = hireDate;
        this.active = active;
    }

    // Getters
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getPaternalLastName() { return paternalLastName; }
    public String getMaternalLastName() { return maternalLastName; }
    public Integer getAge() { return age; }
    public String getGender() { return gender; }
    public String getBirthDate() { return birthDate; }
    public String getJobTitle() { return jobTitle; }
    public LocalDateTime getHireDate() { return hireDate; }
    public Boolean getActive() { return active; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setPaternalLastName(String paternalLastName) { this.paternalLastName = paternalLastName; }
    public void setMaternalLastName(String maternalLastName) { this.maternalLastName = maternalLastName; }
    public void setAge(Integer age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public void setHireDate(LocalDateTime hireDate) { this.hireDate = hireDate; }
    public void setActive(Boolean active) { this.active = active; }
}