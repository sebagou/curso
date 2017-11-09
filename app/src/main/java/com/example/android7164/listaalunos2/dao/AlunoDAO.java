package com.example.android7164.listaalunos2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android7164.listaalunos2.model.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android7164 on 07/11/17.
 */

public class AlunoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 2;
    private static final String TABELA = "Alunos";
    private static final String DATABASE = "CadstroCaelum";

    public AlunoDAO(Context context){

        super(context, DATABASE,  null, VERSAO);

    }

    public void onCreate(SQLiteDatabase database){

        String ddl = "CREATE TABLE " + TABELA
                + " (id INTEGER PRIMARY KEY, "
                + " nome TEXT NOT NULL, "
                + " telefone TEXT, "
                + " endereco TEXT, "
                + " site TEXT, "
                + " nota REAL,"
                + "caminhoFoto TEXT);";

        database.execSQL(ddl);

    }

    public void onUpgrade(SQLiteDatabase database, int versaoAntiga, int versaoNova){

        if (versaoAntiga ==1) {
            String sql = "ALTER TABLE " + TABELA + " ADD COLUMN caminhoFoto TEXT;";
            database.execSQL(sql);
        }
    }

    public void insere(Aluno aluno){
        ContentValues valores = new ContentValues();

        valores.put("nome", aluno.getNome());
        valores.put("endereco", aluno.getEndereco());
        valores.put("telefone", aluno.getTelefone());
        valores.put("site", aluno.getSite());
        valores.put("nota", aluno.getNota());
        valores.put("caminhoFoto", aluno.getFoto());

        getWritableDatabase().insert(TABELA, null, valores);


    }

    public List<Aluno> getLista(){
        List<Aluno> alunos = new ArrayList<Aluno>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABELA + ";", null);

        while(c.moveToNext()){
            Aluno aluno = new Aluno();

            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            aluno.setFoto(c.getString(c.getColumnIndex("caminhoFoto")));

            alunos.add(aluno);

        }
        c.close();
        return alunos;
    }

    public void exclui(Aluno aluno){

        String[] id = {aluno.getId().toString()};

        getWritableDatabase().delete(TABELA, "id=?", id);

    }

    public void atualiza(Aluno aluno){
        ContentValues valores = new ContentValues();

        valores.put("nome", aluno.getNome());
        valores.put("endereco", aluno.getEndereco());
        valores.put("telefone", aluno.getTelefone());
        valores.put("site", aluno.getSite());
        valores.put("nota", aluno.getNota());
        valores.put("id", aluno.getId());
        valores.put("caminhoFoto", aluno.getFoto());

        String[] id = {aluno.getId().toString()};

        getWritableDatabase().update(TABELA, valores, "id=?", id);


    }
}
