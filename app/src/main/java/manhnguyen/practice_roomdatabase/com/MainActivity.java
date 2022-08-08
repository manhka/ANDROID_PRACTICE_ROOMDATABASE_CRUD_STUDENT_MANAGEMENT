package manhnguyen.practice_roomdatabase.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import manhnguyen.practice_roomdatabase.com.DataBase.StudentDatabase;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_EDIT = 456;
    ImageButton btnAdd, btnSearch;
    RecyclerView viewListStudent;
    EditText inputSearch;
    List<Student> students;
    StudentAdapter adapter;
    private static int REQUEST_CODE_ADD = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();
        students = new ArrayList<>();
        adapter = new StudentAdapter(new StudentAdapter.ClickItemStudent() {
            @Override
            public void deleteStudent(Student student) {
                DeleteStudent(student);
            }

            @Override
            public void editStudent(Student student) {
                EditStudent(student);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        viewListStudent.setLayoutManager(linearLayoutManager);
        viewListStudent.setAdapter(adapter);
        GetListStudent();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewStudent();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchStudentByName();
            }
        });
    }
// function edit student
    private void EditStudent(Student student) {
        Intent intent = new Intent(MainActivity.this, EditStudentMainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("student edit object", student);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE_EDIT);
    }
// function delete student
    private void DeleteStudent(Student student) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Student").
                setMessage("Do you want to remove student : '" + student.getName() + "'?").
                setNegativeButton("Nope!", null).
                setPositiveButton("Yep!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StudentDatabase.getInstance(MainActivity.this).studentDAO().deleteStudent(student);
                        Toast.makeText(MainActivity.this, "Delete Successful!", Toast.LENGTH_SHORT).show();
                        GetListStudent();
                    }
                }).show();
    }
// function search student by name
    private void searchStudentByName() {
        String nameStudentSearch = inputSearch.getText().toString().trim();
        students = new ArrayList<>();
        students = StudentDatabase.getInstance(MainActivity.this).studentDAO().searchByStudentName(nameStudentSearch);
        adapter.setData(students);
        Toast.makeText(this, "Search Successful!", Toast.LENGTH_SHORT).show();
    }

    // add new student function
    private void createNewStudent() {
        Student student = new Student();
        Intent intent = new Intent(MainActivity.this, AddNewStudentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("student add object", student);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE_ADD);
    }
// get result after intending

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null) {
            GetListStudent();
        }
        if (requestCode== REQUEST_CODE_EDIT && resultCode==RESULT_OK && data!=null){
            GetListStudent();
        }
    }

    // get list student function
    private void GetListStudent() {
        students = StudentDatabase.getInstance(this).studentDAO().getListStudent();
        adapter.setData(students);
    }

    private void Mapping() {
        btnAdd = (ImageButton) findViewById(R.id.imageButtonAdd);
        btnSearch = (ImageButton) findViewById(R.id.imageButtonSearch);
        viewListStudent = (RecyclerView) findViewById(R.id.recycleViewList);
        inputSearch = (EditText) findViewById(R.id.editTextSearch);
    }
}