package com.example.android7164.listaalunos2.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.android7164.listaalunos2.converter.AlunoConverter;
import com.example.android7164.listaalunos2.dao.AlunoDAO;
import com.example.android7164.listaalunos2.model.Aluno;
import com.example.android7164.listaalunos2.support.WebClient;

import java.io.IOException;
import java.util.List;

/**
 * Created by android7164 on 09/11/17.
 */

public class EnviaAlunosTask extends AsyncTask<Object, Object, String>{
    private Context context;
    private ProgressDialog progresso;

    public EnviaAlunosTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute(){
        progresso = ProgressDialog.show(this.context, "Aguarde ...","Envio de dados", true, true);
    }

    @Override
    protected String doInBackground(Object[] objects) {

        AlunoDAO dao = new AlunoDAO(this.context);
        List<Aluno> alunos = dao.getLista();
        dao.close();

        String json = new AlunoConverter().toJSON(alunos);


        WebClient client = new WebClient();
        String resposta = null;

        try {
            resposta = client.post("https://www.caelum.com.br/mobile", json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resposta;
    }

    protected void onPostExecute(String resposta){
        Toast.makeText(this.context, "POST:" + resposta, Toast.LENGTH_LONG).show();
        progresso.dismiss();
    }


}
