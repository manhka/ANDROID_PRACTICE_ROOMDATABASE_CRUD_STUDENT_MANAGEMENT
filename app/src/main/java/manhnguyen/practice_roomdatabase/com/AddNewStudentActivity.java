package manhnguyen.practice_roomdatabase.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import manhnguyen.practice_roomdatabase.com.DataBase.StudentDatabase;

public class AddNewStudentActivity extends AppCompatActivity {
    Button btnAddNewStudent;
    EditText studentAddingName, studentAddingClass;
    CircleImageView btnBackToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);
        Mapping();
        btnAddNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentName = studentAddingName.getText().toString().trim();
                String studentClass = studentAddingClass.getText().toString().trim();
                if (studentClass.isEmpty() || studentName.isEmpty()) {
                    Toast.makeText(AddNewStudentActivity.this, "All fields are required!", Toast.LENGTH_LONG).show();
                } else {
                    Student student = (Student) getIntent().getExtras().get("student add object");
                    student.setName(studentName);
                    student.setClazz(studentClass);
                    student.setImage(R.drawable.student2);
                    StudentDatabase.getInstance(AddNewStudentActivity.this).studentDAO().insertData(student);
                    Toast.makeText(AddNewStudentActivity.this, "Add successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        btnBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddNewStudentActivity.this,MainActivity.class));
            }
        });
    }

    private void Mapping() {
        btnAddNewStudent = (Button) findViewById(R.id.btnAdd);
        studentAddingName = (EditText) findViewById(R.id.editTextNameAdd);
        studentAddingClass = (EditText) findViewById(R.id.editTextClassAdd);
        btnBackToHome=(CircleImageView) findViewById(R.id.backToHome);
    }
}