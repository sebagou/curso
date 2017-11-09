package com.example.android7164.listaalunos2.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.android7164.listaalunos2.R;
import com.example.android7164.listaalunos2.activity.FormularioActivity;
import com.example.android7164.listaalunos2.model.Aluno;

/**
 * Created by android7164 on 07/11/17.
 */

public class FormularioHelper {

    private EditText nome;
    private EditText endereco;
    private EditText telefone;
    private EditText site;
    private RatingBar nota;
    private TextInputLayout lnome;
    private Long id;
    private ImageView foto;
    private FloatingActionButton fotoButton;


    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){

        this.aluno = new Aluno();

        this.nome = (EditText) activity.findViewById(R.id.campo_nome);
        this.endereco = (EditText) activity.findViewById(R.id.campo_endereco);
        this.telefone = (EditText) activity.findViewById(R.id.campo_telefone);
        this.site = (EditText) activity.findViewById(R.id.campo_email);
        this.nota = (RatingBar) activity.findViewById(R.id.campo_nota);
        foto = (ImageView) activity.findViewById(R.id.foto);
        fotoButton = (FloatingActionButton) activity.findViewById(R.id.tiraFoto);

       lnome = (TextInputLayout) activity.findViewById(R.id.nome2);

    }

    public Aluno pegaAlunoDoFormulario(){

        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setNota((double) nota.getRating());
        aluno.setId(id);
        aluno.setFoto((String) foto.getTag());

        return aluno;
    }

    public boolean temNome(){
        return !nome.getText().toString().isEmpty();
    }

    public void mostraErro(){
//       nome.setError("Campo nome nao pode ser vazio");

// layout do Textinputlayout
        lnome.setError("Campo nome nao pode ser vazio");
    }

    public void preencheFormulario(Aluno aluno){

        nome.setText(aluno.getNome());
        endereco.setText(aluno.getEndereco());
        telefone.setText(aluno.getTelefone());
        site.setText(aluno.getSite());
        nota.setRating((float) aluno.getNota());
        id = aluno.getId();
        carregaImagem(aluno.getFoto());


    }
    public FloatingActionButton getFotoButton() {
        return fotoButton;
    }

    public void carregaImagem(String caminho) {
        if(caminho !=null){
            Bitmap bitmap = BitmapFactory.decodeFile(caminho);
            bitmap = Bitmap.createScaledBitmap(bitmap,300,300,true);
            foto.setImageBitmap(bitmap);
            foto.setTag(caminho);
        }
    }

}
