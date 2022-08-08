package manhnguyen.practice_roomdatabase.com.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import manhnguyen.practice_roomdatabase.com.Student;

@Database(entities = {Student.class}, version = 3)
public abstract class StudentDatabase extends RoomDatabase {
    private static StudentDatabase instance;

    public static synchronized StudentDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), StudentDatabase.class, "StudentNewData").allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract StudentDAO studentDAO();
}
