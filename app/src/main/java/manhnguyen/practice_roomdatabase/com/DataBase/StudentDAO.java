package manhnguyen.practice_roomdatabase.com.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import manhnguyen.practice_roomdatabase.com.Student;

@Dao
public interface StudentDAO {
    @Insert
    void insertData(Student student);
    @Query("SELECT * FROM student")
    List<Student> getListStudent();
    @Query("SELECT * FROM student WHERE name LIKE '%'||:nameStudent||'%'")
    List<Student> searchByStudentName(String nameStudent);
    @Delete
    void deleteStudent(Student student);
    @Update
    void updateStudent(Student student);
}
