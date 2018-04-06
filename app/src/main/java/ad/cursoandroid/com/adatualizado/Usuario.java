package ad.cursoandroid.com.adatualizado;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Usuario extends BancoDeDados {

    private TextView nomeDoUsuario;


    private EditText textoTarefa;
    private Button boataoAdicionar;
    private ListView lista;

    private SQLiteDatabase banco;

    private ArrayAdapter<String> itensAdaptdor;

    private ArrayList<String> listaItens ;

    private ArrayList<Integer> ids ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        nomeDoUsuario = (TextView) findViewById(R.id.usuarioid);
        Bundle extra = getIntent().getExtras();
        final String usuarioAgora = extra.getString("id");
        int indiceUsuario = Integer.parseInt(usuarioAgora);
        nomeDoUsuario.setText(nomeUsuarioAgora(indiceUsuario));



        try {

            textoTarefa = (EditText) findViewById(R.id.textoid);
            boataoAdicionar = (Button) findViewById(R.id.adicionarid);

            lista = (ListView) findViewById(R.id.listaid);

            Log.i("RESU>TADO","linha1");


            banco = openOrCreateDatabase("apptarefas", MODE_PRIVATE, null);

            Log.i("RESU>TADO","linha3");

            //criando tabela

            banco.execSQL("CREATE TABLE IF NOT EXISTS '"+usuarioAgora+"'(id INTEGER PRIMARY KEY AUTOINCREMENT,tarefa VARCHAR)");

            Log.i("RESU>TADO","linha2");

            boataoAdicionar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String textoDigitado = textoTarefa.getText().toString();
                    //banco.execSQL("INSERT INFO tarefas (tarefa) VALUES('" + textoDigitado + "') ");
                    Toast.makeText(Usuario.this,textoDigitado,Toast.LENGTH_SHORT).show();
                    salvarTarefa(textoDigitado,usuarioAgora);

                }
            });

            lista.setLongClickable(true);
            lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    removerTarefas(ids.get(position),usuarioAgora);
                    return true;
                }
            });


            recuperarTarefas(usuarioAgora);



        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void salvarTarefa(String texto,String usuarioAgora){
        try{
            if (texto == ""){
                Toast.makeText(Usuario.this,"Digite uma tarefa",Toast.LENGTH_SHORT).show();
            }else {

                banco.execSQL("INSERT INTO '"+usuarioAgora+"' (tarefa) VALUES('" + texto + "')");
                Toast.makeText(Usuario.this,"Tarefa Salva",Toast.LENGTH_SHORT).show();
                recuperarTarefas(usuarioAgora);
                textoTarefa.setText("");

            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }


    private void recuperarTarefas(String usuarioAgora){
        try {
            //recupera as tarefas
            Cursor cursor = banco.rawQuery("SELECT * FROM '"+usuarioAgora+"' ORDER BY id DESC ",null);

            //reccupera o id
            int indiceColunaId =  cursor.getColumnIndex("id");
            int indiceColunaTarefa =  cursor.getColumnIndex("tarefa");

            lista = (ListView) findViewById(R.id.listaid);


            listaItens = new ArrayList<String>();
            ids = new ArrayList<Integer>();

            //criar adaptador
            itensAdaptdor = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_list_item_2,
                    android.R.id.text2,
                    listaItens
            );

            lista.setAdapter(itensAdaptdor);

            //listar tarefas
            cursor.moveToFirst();

            while (cursor != null){
                Log.i("resultado - ", "tarefa:"+ cursor.getString(indiceColunaTarefa));

                listaItens.add(cursor.getString(indiceColunaTarefa));
                ids.add(Integer.parseInt( cursor.getString(indiceColunaId)));



                cursor.moveToNext();

            }

        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    public void removerTarefas(Integer id,String usuarioAgora){
        try{
            banco.execSQL("DELETE FROM '"+usuarioAgora+"' WHERE id ="+id);
            recuperarTarefas(usuarioAgora);


        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

