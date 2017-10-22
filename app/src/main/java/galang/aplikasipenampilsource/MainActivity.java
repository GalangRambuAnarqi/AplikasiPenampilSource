package galang.aplikasipenampilsource;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    static TextView myText;
    ConnectInternetTask c1;

    Spinner spinnervar;
//cek koneksi
    ConnectivityManager myConManager;
    NetworkInfo myinfo;
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText=(TextView) findViewById(R.id.myResult);
        myText.setBackgroundResource(R.layout.back);
        final String[] pilihan={"http://","https://"};
        //cek koneksi

        myConManager=(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        myinfo=myConManager.getActiveNetworkInfo();
        //
        final EditText edt=(EditText) findViewById(R.id.editText);
        spinnervar=(Spinner) findViewById(R.id.spinner);
        spinnervar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position){
                    case 0:
                        //Toast.makeText(getApplicationContext(),"http selected",Toast.LENGTH_SHORT).show();
                        edt.setText(""+pilihan[position]);
                        break;

                    case 1:
                        //Toast.makeText(getApplicationContext(),"https selected",Toast.LENGTH_SHORT).show();
                        edt.setText(""+pilihan[position]);
                        break;

                    default:
                        edt.setText("");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void doSomething(View view) {
        c1=new ConnectInternetTask(this);
        EditText edt=(EditText) findViewById(R.id.editText);

        //cek koneksi && cek apakah input text kosong
        if(myinfo!=null&&myinfo.isConnected()){
            if(edt!=null){

                String show=edt.getText().toString();
                c1.execute(show);


            }
        }
        else {
            Toast.makeText(getApplicationContext(), "no internet connection, please check your network", Toast.LENGTH_LONG).show();
        }
        //
    }
}
