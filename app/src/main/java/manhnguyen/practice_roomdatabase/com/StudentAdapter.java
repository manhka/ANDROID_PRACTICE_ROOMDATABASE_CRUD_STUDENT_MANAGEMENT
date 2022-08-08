package manhnguyen.practice_roomdatabase.com;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import manhnguyen.practice_roomdatabase.com.DataBase.StudentDatabase;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<Student> studentList;
    private ClickItemStudent  clickItemStudent;

    public StudentAdapter(ClickItemStudent clickItemStudent) {
        this.clickItemStudent = clickItemStudent;
    }

    public void setData(List<Student> student) {
        this.studentList = student;
        notifyDataSetChanged();
    }
public interface ClickItemStudent{
        void deleteStudent(Student student);
        void editStudent(Student student);
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        Animation animation= AnimationUtils.loadAnimation(view.getContext(),R.anim.animation);
        view.startAnimation(animation);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);
        if (student == null) {
            return;
        }
        holder.studentName.setText(student.getName());
        holder.studentClass.setText(student.getClazz());
        holder.imageStudent.setImageResource(R.drawable.student2);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemStudent.editStudent(student);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               clickItemStudent.deleteStudent(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (studentList != null) {
            return studentList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageStudent;
        TextView studentName, studentClass;
        CircleImageView btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageStudent = (CircleImageView) itemView.findViewById(R.id.image_student);
            studentName = (TextView) itemView.findViewById(R.id.textviewName);
            studentClass = (TextView) itemView.findViewById(R.id.textviewClass);
            btnEdit = (CircleImageView) itemView.findViewById(R.id.imageButtonEditStudent);
            btnDelete = (CircleImageView) itemView.findViewById(R.id.imageButtonDeleteStudent);
        }
    }
}
