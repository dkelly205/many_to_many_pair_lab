import db.DBHelper;
import models.Course;
import models.Instructor;
import models.Lesson;
import models.Student;

import java.util.GregorianCalendar;

public class Runner {

    public static void main(String[] args) {
        Course course1 = new Course("IT", "Msc", new GregorianCalendar(2018,2,12), new GregorianCalendar(2018,6,10));
        Course course2 = new Course("Karate", "BEng", new GregorianCalendar(2018,2,12), new GregorianCalendar(2018,6,12));
        DBHelper.saveOrUpdate(course1);
        DBHelper.saveOrUpdate(course2);

        Instructor instructor1 = new Instructor("Dr Who");
        Instructor instructor2 = new Instructor("Mr Meagi");
        DBHelper.saveOrUpdate(instructor1);
        DBHelper.saveOrUpdate(instructor2);

        Lesson lesson1 = new Lesson("DHCP", 7, course1, instructor1);
        Lesson lesson2 = new Lesson("DNS", 7, course1, instructor1);
        Lesson lesson3 = new Lesson("Paint the fence", 1, course2, instructor2);
        DBHelper.saveOrUpdate(lesson1);
        DBHelper.saveOrUpdate(lesson2);
        DBHelper.saveOrUpdate(lesson3);

        Student student1 = new Student("Danny", 27, 101, course1);
        Student student2 = new Student("Daniel Son", 18, 10, course2);

        DBHelper.addStudentToLesson(student1, lesson1);
        DBHelper.addStudentToLesson(student2, lesson1);
        DBHelper.addStudentToLesson(student1, lesson2);

        Student foundStudent = DBHelper.find(Student.class, student1.getId());

        Lesson foundLesson = DBHelper.find(Lesson.class, lesson1.getId());
    }
}
