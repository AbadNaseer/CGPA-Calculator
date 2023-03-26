package application;

public class Course {
    private String name;
    private String grade;
    private int creditHour;

    public Course(String name, String grade, int creditHour) {
        this.name = name;
        this.grade = grade;
        this.creditHour = creditHour;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public int getCreditHour() {
        return creditHour;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }

    @Override
    public String toString() {
        return name + ": " + grade + " (" + creditHour + " credits)";
    }
}
