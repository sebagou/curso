package com.example.android7164.listaalunos2.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android7164.listaalunos2.R;
import com.example.android7164.listaalunos2.dao.AlunoDAO;
import com.example.android7164.listaalunos2.helper.FormularioHelper;
import com.example.android7164.listaalunos2.model.Aluno;

import java.io.File;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    //guardando local no qual queremos gravar a foto
    private String caminho;
    private static final int TIRA_FOTO = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        this.helper = new FormularioHelper(this);

        Intent intent = getIntent();
        if (intent.hasExtra("Aluno")){
            Aluno aluno = (Aluno) intent.getSerializableExtra("Aluno");
            helper.preencheFormulario(aluno);
        }


        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        //Código para botão para tirar foto
        FloatingActionButton foto = helper.getFotoButton();
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caminho = getExternalFilesDir(null)+"/"+System.currentTimeMillis()+".jpg";
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uriDoArquivo = Uri.fromFile(new File(caminho));
                camera.putExtra(MediaStore.EXTRA_OUTPUT, uriDoArquivo);
                startActivityForResult(camera, TIRA_FOTO);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){


        switch (item.getItemId()) {
            case R.id.menu_fomulario_ok:

                Aluno alunoForm = helper.pegaAlunoDoFormulario();
                AlunoDAO alunodb = new AlunoDAO(this);

                if(alunoForm.getId() == null) {
                    if (helper.temNome()) {
                        alunodb.insere(alunoForm);
                        Toast.makeText(this, "Aluno salvo", Toast.LENGTH_SHORT).show();
                        finish();
                        return false;
                    } else {
                        helper.mostraErro();
                    }
                }
                else {
                        alunodb.atualiza(alunoForm);
                        Toast.makeText(this, "Aluno salvo", Toast.LENGTH_SHORT).show();
                        finish();
                        return false;
                    }



            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultado) {
        if(requestCode == TIRA_FOTO) {
            if(resultCode == Activity.RESULT_OK) {
                helper.carregaImagem(caminho);
            }
        }
    }
}
