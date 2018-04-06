package ad.cursoandroid.com.adatualizado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Novo_usuario extends BancoDeDados {
    private EditText novoNome;
    private EditText novoLogin;
    private EditText novaSenha;
    private TextView textologin;
    private Button botaoCriar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

       // criandoBanco();
        // motandoTabela();


        novoNome = (EditText) findViewById(R.id.novonomeid);
        novoLogin = (EditText) findViewById(R.id.novologinid);
        novaSenha = (EditText) findViewById(R.id.novasenhaid);

        textologin = (TextView) findViewById(R.id.verificaçaologinid);

        botaoCriar = (Button) findViewById(R.id.botaocriarid);
        botaoCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novoNomeDigitado = novoNome.getText().toString();
                String loginDigitado = novoLogin.getText().toString();
                String senhaDigitado = novaSenha.getText().toString();
                //Toast.makeText(Novo_usuario.this,novoNomeDigitado,Toast.LENGTH_SHORT).show();


                int indice = verificacao(loginDigitado,null,2);
                //String ind =Integer.toString(indice);
                Log.i("RESULTADO","voltou");

                //recuperando();

                if(indice == 0){
                    inserirDados(novoNomeDigitado,loginDigitado,senhaDigitado);
                    Toast.makeText(Novo_usuario.this,"Novo Usuario Salvo",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Novo_usuario.this,tela_Login.class));
                }else {
                    novoLogin.setText("");
                    textologin.setText(" Usuario ja Existe ");

                }
                //recreate();
                //recuperando();





            }
        });

    }

}
