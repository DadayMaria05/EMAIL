package silva.oliveira.maria.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button BtnEnviar = (Button) findViewById(R.id.btnEnviar);
        //definicao da acao do click do botao
        BtnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtendo dados digitados pelo usuario em email
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();

                //obtendo dados digitados pelo usuario em assunto
                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                //obtendo dados digitados pelo usuario em texto
                EditText editText = (EditText) findViewById(R.id.etTexto);
                String texto = editText.getText().toString();

                //declarando variavel que indica a acao que queremos realizar
                Intent i = new Intent(Intent.ACTION_SENDTO);

                //indica apps que trabalham com envio e recebimento de e-mail.
                i.setData(Uri.parse("mailto:"));

                //preenchimento do Intent com os dados que queremos enviar para a app externa que vai enviar o e-mail.
                String[] emails = new String[] {email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                //tentativa de executar o Intent
                try {
                    startActivity(Intent.createChooser(i, "Escolha o app"));
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this,"não há nenhum app que posso realizar", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
