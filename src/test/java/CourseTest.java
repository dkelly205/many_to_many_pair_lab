import db.DBHelper;
import models.Course;
import models.Lesson;
import models.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CourseTest {

    Course course;
    private Student student;
    private Lesson lesson;

    @Before
    public void setUp() throws Exception {
        course = new Course("Software Development", "PDA");
        DBHelper.saveOrUpdate(course);
        student = new Student("Danny", 27, 12, course);
        DBHelper.saveOrUpdate(student);
        lesson = new Lesson("Many to Many", 101, course);

    }

    @After
    public void tearDown() {
        DBHelper.delete(student);
        DBHelper.delete(course);

    }

    @Test
    public void testHasSaved() {
        List<Course> result = DBHelper.getAll(Course.class);
        assertEquals(1, result.size());
    }

    @Test
    public void testCourseHasStudents() {
        List<Student> result = DBHelper.getStudentsFromCourse(course);
        assertEquals(1, result.size());
//        Student foundStudent = result.get(0);
//        assertEquals("Danny", foundStudent.getName());
    }

    @Test
    public void testCourseHasLessons() {
        List<Lesson> result = DBHelper.getLessonsFromCourse(course);
        assertEquals(1, result.size());
    }
}
