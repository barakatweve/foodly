package com.example.becks.materia.IDM;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.becks.materia.R;
import com.example.becks.materia.restauraunts.chipskavu;
import com.example.becks.materia.restauraunts.tambinyama;
import com.example.becks.materia.restauraunts.ugali;
import com.example.becks.materia.restauraunts.walikuku;
import com.example.becks.materia.restauraunts.walinyama;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Idm extends AppCompatActivity {
        Context context;
    String myjresul=null;
    private String url = "http://myfirstdatabase.esy.es/fetchData.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idm);
        getData();


       }

    public void getData() {
        class GetDataJSON extends AsyncTask<String, Void, String> {
            String line=null;
            ProgressDialog progressDialog;
            private StringBuilder sb=null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                progressDialog=new ProgressDialog(context);
//                progressDialog.setTitle("Dowloading data");
//                progressDialog.setMessage("Please wait...");
//                progressDialog.show();

            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    URL urlConnection = new URL(url);
                    try {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection.openConnection();
                        InputStream is = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

                        if(bufferedReader !=null){
                             sb = new StringBuilder();
                            while ((line = bufferedReader.readLine()) != null) {
                                sb.append(line + "\n");
                            }
                        }
                        else{
                            Toast.makeText(context,"no data readed",Toast.LENGTH_SHORT).show();
                        }

                        bufferedReader.close();
                        if (is!=null){
                            is.close();
                            httpURLConnection.disconnect();
                        }
                       String jsonResult=null;
                        jsonResult=sb.toString().trim();
                        return jsonResult;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return null;
            }


            @Override
            protected void onPostExecute(String result) {

if (result !=null){

    myjresul=result;
//progressDialog.dismiss();

}
                else {
    Toast.makeText(context,"network error",Toast.LENGTH_SHORT).show();
}



            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

    public void displayList(View v){

if ( myjresul!=null){

    Intent intent=new Intent(this,displayListView.class);
    intent.putExtra("myjson",myjresul);
    startActivity(intent);
}
        else
{
    Toast.makeText(getApplicationContext(),"reload again the menu",Toast.LENGTH_LONG).show();
}
}


    /**
     * Created by becks on 5/6/16.
     */
    // this class for populating the items on the list view
    public static class displayListView extends AppCompatActivity {
        String jsonData;
        JSONArray jsonArray;
        JSONObject jsonObject;
        listAdapter lsAdapter;
        ListView listView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.display_list_view);
            jsonData = getIntent().getExtras().getString("myjson");
            lsAdapter = new listAdapter(this, R.layout.idm_items);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(lsAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   TextView txt= (TextView) view.findViewById(R.id.name);
                 // String name= (String) parent.getItemAtPosition(position);
    //

                    if (txt.getText().equals("chips kavu")){
                        startActivity(new Intent(getApplicationContext(),chipskavu.class));
                    }

                        else if (txt.getText().equals("tambi nyama")){
                            startActivity(new Intent(getApplicationContext(),tambinyama.class));

                        }

                    else if (txt.getText().equals("wali nyama")){
                        startActivity(new Intent(getApplicationContext(),walinyama.class));

                    }
                    else if (txt.getText().equals("wali kuku")){
                        startActivity(new Intent(getApplicationContext(),walikuku.class));

                    }
                    else if (txt.getText().equals("pilau nyama")){
                        startActivity(new Intent(getApplicationContext(),walinyama.class));

                    }



                  //  }

                  //  {
                       // startActivity(new Intent(getApplicationContext(),walikuku.class));

                    }

    //                switch (name.trim()){
    //                    case name.equals("baaka"):
    //                    case name.equals(""):
    //                      startActivity(new Intent(getApplicationContext(),walinyama.class));
    //                        break;
    //                    case "zuhura":
    //                        startActivity(new Intent(getApplicationContext(),walikuku.class));
    //                        break;
    //                    case "ugali":
    //                        startActivity(new Intent(getApplicationContext(),ugali.class));
    //                        break;
    //                    default:
    //                        Toast.makeText(getApplicationContext(),"NOTHING CLICKED",Toast.LENGTH_LONG).show();
    //
    //                }

            });

            if (jsonData == null) {
                Toast.makeText(this, "null data", Toast.LENGTH_LONG).show();
            } else {

                try {

                    jsonObject = new JSONObject(jsonData); // to pass json objects
                    jsonArray = jsonObject.getJSONArray("result");
                    // to get all jsonsdata
                    int count = 0;
                    String name;
                    String address;
                    String id;
                    while (count < jsonArray.length()) {

                        JSONObject jObject = jsonArray.getJSONObject(count);

                        name = jObject.getString("name");
                        idmSetGet co = new idmSetGet(name);
                        lsAdapter.add(co);
                        count++;


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Created by becks on 5/7/16.
     */
    public static class idmSetGet {

        String name,address;
        String id;
        idmSetGet( String name){
            this.setName(name);
            this.setAddress(address);
            this.setId(id);

        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public String getName() {
            return name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setId(String id) {
            this.id=id;
        }

        public String getId() {
            return id;
        }
    }

    /**
     * Created by becks on 5/6/16.
     */
    //
    public static class listAdapter extends ArrayAdapter {
            List lists = new ArrayList();


            public listAdapter(Context context, int resource) {

                    super(context, resource);
            }

            public void add(idmSetGet object) {
                    super.add(object);
                    lists.add(object);

            }

            @Override
            public Object getItem(int position) {

                    return lists.get(position);

            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                    myholder holder;
                    View row;
                    row=convertView;
                    if(row==null){
                            LayoutInflater inflater= (LayoutInflater) this.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                            row=inflater.inflate(R.layout.idm_items,parent,false);
                            holder=new myholder();
    //                        holder.id= (TextView) row.findViewById(R.id.id);
                            holder.txt_name= (TextView) row.findViewById(R.id.name);
    //                        holder.txt_address= (TextView) row.findViewById(R.id.address);

                            row.setTag(holder);
                    }

                    else{
                            holder= (myholder) row.getTag();
                    }
                    // to get the item in each posutions
                    idmSetGet co= (idmSetGet) getItem(position);
    //                holder.id.setText(co.getId());
                    holder.txt_name.setText(co.getName());
    //                holder.txt_address.setText(co.getAddress());

                    return row;
            }

            @Override
            public int getCount() {
                    return lists.size();
            }
            static class myholder{
                    TextView txt_name;
                    TextView txt_address;
                   //  TextView id;
            }
    }
}
