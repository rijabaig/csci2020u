package com.lab08;

public class StudentRecord {
    private String id;
    private Float assignment;
    private Float finalMark;
    private Float exam;
    private Float midterm;

    public StudentRecord(String id, float assignment, float midterm, float exam) {
        this.id = id;
        this.assignment = assignment;
        this.midterm = midterm;
        this.exam = exam;
        this.finalMark = assignment * 0.2f + midterm * 0.3f + exam * 0.5f;
        makeLetter(this.finalMark);
    }

    public char makeLetter(float g) {
        if (g < 50.0f) {
            return 'F';
        }
        if (50.0f <= g && g < 60.0f) {
            return 'D';
        }
        if (g >= 60.0f && g < 70.0f) {
            return 'C';
        }
        if (g >= 70.0f && g < 80.0f) {
            return 'B';
        } else {
            return 'A';
        }
    }

    public float getmidterm() {
        return midterm;
    }

    public void setmidterm(float midterm) {
        this.midterm = midterm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getFinalExam() {
        return exam;
    }

    public void setFinalExam(float exam) {
        this.exam = exam;
    }

    public float getassignment() {
        return assignment;
    }

    public void setassignment(float assignment) {
        this.assignment = assignment;
    }

    public float gettotal() {
        return finalMark;
    }

    public char getLetterGrade() {
        return makeLetter(gettotal());
    }
}
