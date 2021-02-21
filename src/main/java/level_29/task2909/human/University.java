package level_29.task2909.human;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class University{

    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name=name;
        this.age=age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for(Student x: students){
            if (x.getAverageGrade()==averageGrade) return x;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        return students.stream().max(Comparator.comparingDouble(Student::getAverageGrade)).get();
    }
    public Student getStudentWithMinAverageGrade() {
        return students.stream().min(Comparator.comparingDouble(Student::getAverageGrade)).get();
    }

    public void expel(Student student) {
        students.remove(student);
    }
}
