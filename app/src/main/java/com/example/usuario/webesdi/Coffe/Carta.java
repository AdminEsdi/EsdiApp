package com.example.usuario.webesdi.Coffe;



import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.webesdi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class Carta extends Fragment{
    private RecyclerView recyclerView;
    private List<ConstructorCarta> carta;
    private LinearLayoutManager linearLayoutManager;
    private MyAdapterCarta adapter;

    /*@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_carta, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_carta);
        carta = new ArrayList<>();
        //ListView lst = (ListView) getView().findViewById(R.id.listaCarta);

        load_data_from_server(0);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyAdapterCarta(getContext(),carta);
        recyclerView.setAdapter(adapter);
        return rootView;
    }


    private void load_data_from_server(int id) {

        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url("http://67.222.58.123/ddt/sql/cafeteriaCarta.php")
                        .build();
                try {
                    okhttp3.Response response = client.newCall(request).execute();
                    JSONArray array = new JSONArray(response.body().string());
                    for (int i=0; i<array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        ConstructorCarta data = new ConstructorCarta(object.getInt("id"),object.getString("cafeCartaNombreENG"),object.getString("cafeCartaNombreESP"),object.getString("cafeCartaNombreCAT"),object.getString("cafeCartaPrecio"),object.getString("cafeCartaDescripENG"),object.getString("cafeCartaDescripESP"),object.getString("cafeCartaDescripCAT"));
                        carta.add(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of content");
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        };
        task.execute(id);
    }
}
