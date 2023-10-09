package com.daneshnaik.chatbot.Activities.Departments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.daneshnaik.chatbot.R;
import com.daneshnaik.chatbot.Tables.pdfclass;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Mechanical extends AppCompatActivity {
    TextInputEditText pdf_name;
    AppCompatButton upload;
    TextView error_apti;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanical);
         pdf_name=findViewById(R.id.pdf_name_uploader);
         upload=findViewById(R.id.uploadfile);
         error_apti=findViewById(R.id.error_apti);
       databaseReference= FirebaseDatabase.getInstance().getReference("uploads");
       String pdf_name_taken=pdf_name.getEditableText().toString();

           upload.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   selectFile();

               }
           });
       }



    private void selectFile() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select pdf File.."),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==1 && data!=null && data.getData()!=null){
            UploadFiles(data.getData());
        }
    }

    private void UploadFiles(Uri data) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uploading File"+pdf_name.getEditableText().toString());
        progressDialog.show();
        StorageReference reference= FirebaseStorage.getInstance().getReference("fileuploads").child(pdf_name.getEditableText().toString()+" "+System.currentTimeMillis()+".pdf");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri url=uriTask.getResult();
                pdfclass Pdfclass=new pdfclass(pdf_name.getEditableText().toString(),url.toString());
                databaseReference.child(databaseReference.push().getKey()).setValue(Pdfclass);
                Toast.makeText(getApplicationContext(),"File Uploaded",Toast.LENGTH_SHORT).show();
                pdf_name.setText(null);
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress=(100.0*snapshot.getBytesTransferred())/ snapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded  "+(int)progress+"%");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
            }
        });
    }
}
