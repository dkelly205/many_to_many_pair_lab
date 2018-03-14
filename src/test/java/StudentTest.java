import db.DBHelper;
import models.Course;
import models.Lesson;
import models.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StudentTest {

    private Student student;
    private Lesson lesson;
    private Course course;

    @Before
    public void setUp() throws Exception {
        course = new Course("Software Development", "MSc");
        lesson = new Lesson("Many to Many", 12, course);
        student = new Student("Danny", 27, 101, course);
        DBHelper.saveOrUpdate(course);
        DBHelper.saveOrUpdate(lesson);
        DBHelper.saveOrUpdate(student);
    }

    @After
    public void tearDown() {
        DBHelper.delete(lesson);
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
