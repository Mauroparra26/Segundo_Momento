package com.example.appinvoicing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registerproduct extends AppCompatActivity implements  Response.ErrorListener, Response.Listener<JSONObject> {

    EditText referencia, nombre, precio, stock, id;
    Button btnadd, btnsearch, btndelete, btnedit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerproduct);

        id = findViewById(R.id.etidproduct);
        referencia = findViewById(R.id.etreferencia);
        nombre = findViewById(R.id.etnombreproducto);
        precio = findViewById(R.id.etprecio);
        stock = findViewById(R.id.etstock);
        btnadd = findViewById(R.id.btnagregar);
        btndelete = findViewById(R.id.btndelete);
        btnsearch = findViewById(R.id.btnbuscar);
        btnedit = findViewById(R.id.btneditar);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addproduct(nombre.getText().toString(), referencia.getText().toString(), precio.getText().toString(), stock.getText().toString() );
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Deleteproduct(id.getText().toString());
            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchProduct(referencia.getText().toString());
            }
        });

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProduct(id.getText().toString(), referencia.getText().toString(), nombre.getText().toString(), precio.getText().toString(), stock.getText().toString());
            }
        });

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void addproduct(String nombreproducto, String areferencia, String aprecio, String astock) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            if (!nombreproducto.isEmpty() && !areferencia.isEmpty() && !aprecio.isEmpty() && !astock.isEmpty()) {

                String url = "http://192.168.31.1:80/invoicing/../products/addproduct.php";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), "Registro de producto realizado correctamente!", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Registro de producto incorrecto!", Toast.LENGTH_LONG).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("referencia", areferencia);
                        params.put("nombre", nombreproducto);
                        params.put("precio", aprecio);
                        params.put("stock", astock);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(postRequest);
            }
            else{
                Toast.makeText(getApplicationContext(),"Debe ingresar todos los datos...",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void Deleteproduct(String id) {
        String url = "http://192.168.31.1:80/invoicing/../products/deleteproduct.php?id="+id;
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Producto eliminado correctamente!", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Producto incorrecto!", Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(postRequest);
    }

    private void updateProduct(String uid, String ureferencia, String unombre, String uprecio, String ustock) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            if(!ureferencia.isEmpty() && !unombre.isEmpty() && !uprecio.isEmpty() && !ustock.isEmpty()){
                String url = "http://192.168.31.1:80/invoicing/../products/updateproduct.php";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), "Registro de producto correcto", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Registro de producto falló", Toast.LENGTH_LONG).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("id",uid);
                        params.put("referencia",ureferencia);
                        params.put("nombre", unombre);
                        params.put("precio",uprecio);
                        params.put("stock",ustock);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(postRequest);
            }
            else{
                Toast.makeText(getApplicationContext(), "Debe ingresar todos los datos", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void searchProduct(String breferencia) {
        String url = "http://192.168.31.1:80/invoicing/../searchproduct.php?referencia="+breferencia;
        // requermiento del servidor a traves de una api por el metodo get, manda la informacion en formato jSON ingresa en on response
        JsonRequest jrq = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                products bproducts =new products();

                JSONArray arrProduct=response.optJSONArray("customer");
                JSONObject jsonObject=null;

                try {
                    jsonObject = arrProduct.getJSONObject(0);//posición 0 del arreglo....
                    bproducts.setId(jsonObject.optString("id"));
                    bproducts.setReferencia(jsonObject.optString("referencia"));
                    bproducts.setNombre(jsonObject.optString("nombre"));
                    bproducts.setPrecio(jsonObject.optString("precio"));
                    bproducts.setStock(jsonObject.optString("stock"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                id.setText(bproducts.getId());
                referencia.setText(bproducts.getReferencia());//SE MODIFICA
                nombre.setText(bproducts.getNombre());//SE MODIFICA
                precio.setText(bproducts.getPrecio());//SE MODIFICA
                stock.setText(bproducts.getStock());//SE MODIFICA
                Toast.makeText(getApplicationContext(),id.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error, no se encuentra la referencia: "+ breferencia, Toast.LENGTH_LONG).show();
            }
        });
        // hacer la peticion por el metdo GET
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(jrq); // manda a ejecutar la linea anterior

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}