package manhnguyen.practice_roomdatabase.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import manhnguyen.practice_roomdatabase.com.DataBase.StudentDatabase;

public class EditStudentMainActivity extends AppCompatActivity {
    Button btnEditStudent;
    EditText studentEditingName, studentEditingClass;
    CircleImageView btnBackToHomeFormEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student_main);
        Mapping();
        Student  student= (Student) getIntent().getExtras().get("student edit object");
        studentEditingName.setText(student.getName());
        studentEditingClass.setText(student.getClazz());
       btnEditStudent.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String newStudentName=studentEditingName.getText().toString().trim();
               String newStudentClass=studentEditingClass.getText().toString().trim();
               int image=R.drawable.student2;
               if (newStudentClass.isEmpty()||newStudentName.isEmpty()){
                   Toast.makeText(EditStudentMainActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
               }
               else {
                   student.setName(newStudentName);
                   student.setClazz(newStudentClass);
                   student.setImage(image);
                   StudentDatabase.getInstance(view.getContext()).studentDAO().updateStudent(student);
                   Toast.makeText(EditStudentMainActivity.this, "Edit Student Successful!", Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent();
                   setResult(RESULT_OK,intent);
                   finish();
               }
           }
       });
       btnBackToHomeFormEdit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(EditStudentMainActivity.this,MainActivity.class));
           }
       });
    }
    private void Mapping() {
        btnEditStudent = (Button) findViewById(R.id.btnEdit);
        studentEditingName = (EditText) findViewById(R.id.editTextNameEdit);
        studentEditingClass = (EditText) findViewById(R.id.editTextClassEdit);
        btnBackToHomeFormEdit=(CircleImageView) findViewById(R.id.backToHomeFromEditSite);
    }
}