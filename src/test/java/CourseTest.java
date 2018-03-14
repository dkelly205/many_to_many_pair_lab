import db.DBHelper;
import models.Course;
import models.Instructor;
import models.Lesson;
import models.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CourseTest {

    Course course;
    private Student student;
    private Lesson lesson;
    private Instructor instructor;

    @Before
    public void setUp() throws Exception {
        course = new Course("Software Development", "PDA", new GregorianCalendar(2018,2,12), new GregorianCalendar(2018,5,4));
        DBHelper.saveOrUpdate(course);
        instructor = new Instructor("Dr Who");
        student = new Student("Danny", 27, 12, course);
        DBHelper.saveOrUpdate(student);
        lesson = new Lesson("Many to Many", 101, course, instructor);
        DBHelper.saveOrUpdate(lesson);

    }

    @After
    public void tearDown() {
        DBHelper.delete(student);
        DBHelper.delete(lesson);
        DBHelper.delete(instructor);
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

    @Test
    public void testCourseStartEnd(){
        assertEquals(new GregorianCalendar(2018,2,12), course.getStartDate());
    }

    @Test
    public void testCourseEndDate(){
        assertEquals(new GregorianCalendar(2018,5,4), course.getEndDate());
    }
}
