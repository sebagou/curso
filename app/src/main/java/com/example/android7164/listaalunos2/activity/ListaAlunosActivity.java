package com.example.android7164.listaalunos2.activity;

// Lista de alunos

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android7164.listaalunos2.R;
import com.example.android7164.listaalunos2.adapter.ListaAlunosAdapter;
import com.example.android7164.listaalunos2.converter.AlunoConverter;
import com.example.android7164.listaalunos2.dao.AlunoDAO;
import com.example.android7164.listaalunos2.model.Aluno;
import com.example.android7164.listaalunos2.permission.Permissao;
import com.example.android7164.listaalunos2.support.WebClient;
import com.example.android7164.listaalunos2.task.EnviaAlunosTask;

import java.io.IOException;
import java.util.List;
import java.util.jar.Manifest;

public class ListaAlunosActivity extends AppCompatActivity {
    private ListView listAlunos;
    private List<Aluno> alunos;

    // lista de alunos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);


        listAlunos = (ListView) findViewById(R.id.lista_alunos);

        registerForContextMenu(listAlunos);

        //permissoes para o app
        Permissao.fazPermissao(this);

        listAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Aluno aluno = (Aluno) adapterView.getItemAtPosition(pos);
                Toast.makeText(ListaAlunosActivity.this, aluno.getNome(), Toast.LENGTH_SHORT).show();

                Intent edicao = new Intent(ListaAlunosActivity.this, FormularioActivity.class);

                edicao.putExtra("Aluno", aluno);
                startActivity(edicao);;

            }
        });

        listAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Aluno aluno = (Aluno) adapterView.getItemAtPosition(pos);
                Toast.makeText(ListaAlunosActivity.this, "Nota: " + aluno.getNota(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        // botao adicionar
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.botao_mais);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(irFormulario);
            }
        });


    }

    protected void onResume() {

        // recarrega a lista
        super.onResume();
        this.carregaLista();
    }

    private void carregaLista() {
        // carregar lista de alunos

        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getLista();
        dao.close();

//        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);

        ListaAlunosAdapter adapter = new ListaAlunosAdapter(this, alunos);

        this.listAlunos.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;

        final Aluno aluno = (Aluno) listAlunos.getItemAtPosition(position);

        MenuItem ligar = menu.add("Ligar");
        MenuItem enviarSMS = menu.add("Enviar SMS");
        MenuItem achar = menu.add("Achar no Mapa");
        MenuItem site = menu.add("Navegar no site");
        MenuItem deletar = menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.exclui(aluno);
                dao.close();
                carregaLista();
                return true;
            }
        });



        Intent intentLigar = new Intent(Intent.ACTION_CALL);
        intentLigar.setData(Uri.parse("tel:" + aluno.getTelefone()));
        ligar.setIntent(intentLigar);

        //Código para a opção de envio de SMS
        Intent intentSms = new Intent(Intent.ACTION_VIEW);
        intentSms.setData(Uri.parse("sms:"+aluno.getTelefone()));
        intentSms.putExtra("sms_body", "Mensagem");
        enviarSMS.setIntent(intentSms);

        //Código para a opção Achar no Mapa
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        String endereco=aluno.getEndereco();
        intentMapa.setData(Uri.parse("geo:0,0?z=14&q=" + Uri.encode(endereco)));
        achar.setIntent(intentMapa);

        //Código para opção de abrir o site

        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        intentSite.setData(Uri.parse("http:"+aluno.getSite()));
        site.setIntent(intentSite);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){

            case R.id.menu_enviar_notas:
                new EnviaAlunosTask(this).execute();
                return true;

            case R.id.menu_receber_provas:
                Intent provas = new Intent(this, ProvasActivity.class);
                startActivity(provas);
                return true;

        }
        return super.onOptionsItemSelected(item);

    }
}







