import db.DBHelper;
import models.Course;
import models.Instructor;
import models.Lesson;
import models.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static junit.framework.Assert.assertEquals;

public class StudentTest {

    private Student student;
    private Lesson lesson;
    private Course course;
    private Instructor instructor;

    @Before
    public void setUp() throws Exception {
        course = new Course("Software Development", "MSc", new GregorianCalendar(2018,2,12), new GregorianCalendar(2018,6,10));
        instructor = new Instructor("Dr Who");
        lesson = new Lesson("Many to Many", 12, course, instructor);
        student = new Student("Danny", 27, 101, course);
        DBHelper.saveOrUpdate(course);
        DBHelper.saveOrUpdate(instructor);
        DBHelper.saveOrUpdate(lesson);
        DBHelper.saveOrUpdate(student);
    }

    @After
    public void tearDown() {
        DBHelper.delete(lesson);
        DBHelper.delete(instructor);
        DBHelper.delete(student);
        DBHelper.delete(course);

    }

    @Test
    public void testStudentHasLessons() {
        DBHelper.addStudentToLesson(student, lesson);
        Student foundStudent = DBHelper.find(Student.class, student.getId());
        assertEquals(1, foundStudent.getLessons().size());
    }
}
