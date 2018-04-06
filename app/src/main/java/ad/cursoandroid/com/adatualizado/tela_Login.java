package ad.cursoandroid.com.adatualizado;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class tela_Login extends BancoDeDados{
    private EditText usuario;
    private EditText senha;
    private TextView aindanaoecadastrado;
    private Button entrar;
    private TextView textoVerificacao;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__login);

        criandoBanco();
        motandoTabela();

        aindanaoecadastrado = (TextView) findViewById(R.id.textonovousuarioid);

        aindanaoecadastrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(tela_Login.this,Novo_usuario.class));
            }

        });

        usuario = (EditText) findViewById(R.id.usuarioid);
        senha = (EditText) findViewById(R.id.senhaid);
        entrar = (Button) findViewById(R.id.entrarid);
        textoVerificacao =(TextView) findViewById(R.id.textoverificaoid);


        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginDigitado = usuario.getText().toString();
                String senhatentativa = senha.getText().toString();



                int ver = verificacao(loginDigitado,senhatentativa,1);
                if (ver != 0){
                    Intent intent = new Intent(tela_Login.this,Usuario.class);
                    intent.putExtra("id",Integer.toString(ver));
                    startActivity(intent);
                }else{
                    textoVerificacao.setText("Login ou Senha Invalidos");
                }

            }
        });




    }

}
