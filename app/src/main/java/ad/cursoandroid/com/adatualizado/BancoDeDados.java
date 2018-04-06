package ad.cursoandroid.com.adatualizado;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class BancoDeDados extends Activity {

    private static SQLiteDatabase banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        criandoBanco();
        motandoTabela();
    }
    public void deletando(){
        banco.execSQL("DROP TABLE teste");
    }
    public void criandoBanco(){
       banco = openOrCreateDatabase("ActivityDrive", MODE_PRIVATE, null);
    }
    public void motandoTabela(){
        //tabela
        banco.execSQL("CREATE TABLE IF NOT EXISTS teste(id INTEGER PRIMARY KEY AUTOINCREMENT,nome VARCHAR , login VARCHAR , senha VARCHAR)");

    }
    public void inserirDados(String nome,String login,String senha){
        banco.execSQL("INSERT INTO teste(nome,login,senha) VALUES('"+nome+"','"+login+"','"+senha+"')");
    }
    public void recuperando(){

        try {

            //recuperar dados
            Cursor cursor = banco.rawQuery("SELECT * FROM teste ", null);
            int indiceDaColunaNome = cursor.getColumnIndex("nome");
            int indiceDaColunalogin = cursor.getColumnIndex("login");
            int indiceDaColunaSenha = cursor.getColumnIndex("senha");
            int indiceDaColunaId = cursor.getColumnIndex("id");
            cursor.moveToFirst();

            while (cursor != null) {
                Log.i("RESULTADO - nome:", cursor.getString(indiceDaColunaNome));
                Log.i("RESULTADO - login:", cursor.getString(indiceDaColunalogin));
                Log.i("RESULTADO - senha:", cursor.getString(indiceDaColunaSenha));
                Log.i("RESULTADO - id:", cursor.getString(indiceDaColunaId));

                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public int verificacao(String login,String senha,int v){
        int indice = 0;
        try {

            Cursor cursor = banco.rawQuery("SELECT * FROM teste ", null);
            int indiceDaColunaNome = cursor.getColumnIndex("nome");
            int indiceDaColunalogin = cursor.getColumnIndex("login");
            int indiceDaColunaSenha = cursor.getColumnIndex("senha");
            int indiceDaColunaId = cursor.getColumnIndex("id");
            cursor.moveToFirst();

            //testando




            while (cursor != null) {

                if (v==2){
                    if (login.equals(cursor.getString(indiceDaColunalogin))) {
                        indice = Integer.parseInt(cursor.getString(indiceDaColunaId));
                        //Log.i("RESULTADO",cursor.getString(indiceDaColunaId));
                        //Log.i("RESULTADO",Integer.toString(cursor.getPosition()));
                        break;
                    }
                }
                if (v==1){
                    if (login.equals(cursor.getString(indiceDaColunalogin)) && senha.equals(cursor.getString(indiceDaColunaSenha))) {
                        indice = Integer.parseInt(cursor.getString(indiceDaColunaId));
                        //Log.i("RESULTADO","teste v1");
                        break;
                    }

                }

                cursor.moveToNext();

            }

        }finally {
            return indice;
        }





    }
    public String nomeUsuarioAgora(int id){

        Log.i("RESULTADO","1");

        Cursor cursor = banco.rawQuery("SELECT * FROM teste", null);
        int indiceDaColunaNome = cursor.getColumnIndex("nome");

        cursor.moveToFirst();

        for (int i = 1 ; i < id ; i++){
            cursor.moveToNext();
        }


        Log.i("RESULTADO",cursor.getString(indiceDaColunaNome));

        return cursor.getString(indiceDaColunaNome);
    }


}
