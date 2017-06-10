package com.example.manushrivastava.sendformdata;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends Activity {

    static String progress="";
    SigninActivity s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s=new SigninActivity(this,"hello server");
        s.execute("");

    }
    public void SendtoServer(View v)
    {

           if(s.getStatus()==AsyncTask.Status.FINISHED)
           {
               TextView t=(TextView)findViewById(R.id.textView2);
               t.setText(progress+s.getStatus());
           }

    }
}
class SigninActivity extends AsyncTask<String,Void,String>
{
    Context c;
    String m;
    SigninActivity(Context c,String m)
    {   super();
        this.c=c;
        this.m=m;
    }
    @Override
    protected String doInBackground(String... arg0)
    {
        try {

            String link = "http://192.168.43.8/httppost.php";
            String data = URLEncoder.encode("username", "UTF-8") + "=" +URLEncoder.encode(m, "UTF-8");
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = null;
            // Read Server Response
            while ((line = reader.readLine()) != null) {
               
               break;
            }
        }catch(Exception e)
        {

        }
        return null;
    }
}