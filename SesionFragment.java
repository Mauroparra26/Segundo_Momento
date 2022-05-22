package com.example.appinvoicing;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SesionFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    EditText correo,clave;
    Button ingresar;
    TextView registrar, registrarproducto;
    RequestQueue rq; // Petición
    JsonRequest jrq; // recibir la informacion en formato JSON
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sesion, container, false);
        View iniciarSesion = inflater.inflate(R.layout.fragment_sesion, container, false);
        correo = iniciarSesion.findViewById(R.id.etcorreo);
        clave = iniciarSesion.findViewById(R.id.etclave);
        ingresar = iniciarSesion.findViewById(R.id.btningresar);
        registrar = iniciarSesion.findViewById(R.id.tvregistrar);
        registrarproducto = iniciarSesion.findViewById(R.id.tvregistrarproducto);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),register.class)); //para ir a nueva actividad
            }
        });

        registrarproducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),registerproduct.class)); //para ir a nueva actividad
            }
        });

        rq = Volley.newRequestQueue(getContext()); // req. volley
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion(correo.getText().toString(),clave.getText().toString());
            }
        });
        return iniciarSesion;
    }

    private void iniciarSesion(String correo, String clave) {
        String url = "http://172.16.59.115:81/invoicing/searchcustomer.php?email="+correo+"&passwd="+clave;
        jrq = new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        rq.add(jrq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"El usuario con correo "+correo.getText().toString()+
                " NO se ha encontrado",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        //Toast.makeText(getContext(),"El usuario con correo "+correo.getText().toString()+
        //        " se ha encontrado",Toast.LENGTH_SHORT).show();
        customer custom = new customer();
        JSONArray jsonCustomer = response.optJSONArray("customer");
        JSONObject customerObject = null;
        try{
            customerObject = jsonCustomer.getJSONObject(0);//posición 0 del arreglo....
            //custom.setIdcust(customerObject.optString("idcust"));
            custom.setName(customerObject.optString("name"));
            //custom.setEmail(customerObject.optString("email"));
            //custom.setPhone(customerObject.optString("phone"));
            //custom.setPasswd(customerObject.optString("passwd"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        Intent intentMain = new Intent(getContext(),main.class);
        intentMain.putExtra("myname",custom.getName());
        startActivity(intentMain);
    }
}