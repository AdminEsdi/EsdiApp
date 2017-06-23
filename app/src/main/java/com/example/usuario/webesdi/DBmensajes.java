package com.example.usuario.webesdi;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by JR on 21/04/2017.
 */

public class DBmensajes {

    DatabaseReference dbMensajes;
    private ArrayList<String> datos;
    private ArrayList<MensajesIncidencias> datos2;
    String texto;

    private static final String TAGLOG = "firebase-db";


    public DBmensajes(String miTab) {
        //instancia la base de datos de firebase
        dbMensajes = FirebaseDatabase.getInstance().getReference().child(miTab);
        datos = null;
        datos2 = null;
        //texto ="";
    }

    public ArrayList<String> listaCorreos() {

        datos = new ArrayList<String>();
        //el primer dato del spiner sera siempre "todos"
        datos.add("todos");

        Query dbQuery = dbMensajes.orderByKey();
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot nodoUsuario) {
                //reinicia datos para no duplicar las entradas anteriores
                datos.clear();
                datos.add("todos");
                //en cada bucle carga un nuevo elemento a la lista
                for (DataSnapshot childDataSnapshot : nodoUsuario.getChildren()) {
                    if (datos.contains(childDataSnapshot.child("Correo").getValue().toString())) {
                        //    Log.d(TAGLOG, "======= paso por aqui 1 ==========   " );
                        //si el correo ya se ha añadido antes al arraylist, no hace nada, así no se duplica

                    } else {
                        datos.add(childDataSnapshot.child("Correo").getValue().toString());
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
           //     Log.e(TAGLOG, "<------- Error DBmensajes listaCorreos -------->", databaseError.toException());
            }
        };

        //  Log.d(TAGLOG, "======= paso por aqui 2 ==========   " );
        dbQuery.addValueEventListener(eventListener);
        return datos;
    }


    public void listaMensajes(final String correo, final String miTab) {

        //hace una consulta para mostrar los mensajes ordenados por fecha
        Query dbQuery = dbMensajes.orderByKey();


        ValueEventListener eventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot nodoUsuario) {
                datos = new ArrayList<String>();
                texto = "";
                for (DataSnapshot childDataSnapshot : nodoUsuario.getChildren()) {
                    //si el usuario logueado es master y selecciona "todos" en el spinner, muestra todos los mensajes, si no muestra solo
                    //los que coincide su correo (que le pasamos al llamar al procedimiento) con el del correo del mensaje de la DB
                    if (correo.equals("todos")) {
                        datos.add(childDataSnapshot.child("Nombre").getValue() + ": "
                                + childDataSnapshot.child("Mensaje").getValue() + "\n");

                    } else if ((childDataSnapshot.child("Correo").getValue()).equals(correo)) {
                        datos.add(childDataSnapshot.child("Nombre").getValue() + ": "
                                + childDataSnapshot.child("Mensaje").getValue() + "\n");
                    }
                }
              //  Log.d(TAGLOG, "----------------------- pasando1---------------" + datos.size());
                Mensajes.muestraMensajes(datos, miTab);
                //se debe llamar a muestramensajes desde aqui, ya que si se llama listamensajes desde Mensajes,
                //listamensajes devuelve un array vacio primero y despues ya entra en ondatachange para cargar los datos al
                //array
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
             //   Log.d(TAGLOG, "<-------- Error DBmensajes listaMensajes ------>", databaseError.toException());
            }

        };

        dbQuery.addValueEventListener(eventListener);

      //  Log.d(TAGLOG, "----------------------- pasando2---------------");

    }


    public void listaIncidencias(final Context ctx) {

        //hace una consulta para mostrar los mensajes ordenados por fecha
        Query dbQuery = dbMensajes.orderByKey();


        ValueEventListener eventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot nodoUsuario) {
                datos2 = new ArrayList<MensajesIncidencias>();
                for (DataSnapshot childDataSnapshot : nodoUsuario.getChildren()) {
                    String equipo = childDataSnapshot.child("Equipo").getValue().toString();
                    String fecha = (String) childDataSnapshot.child("Fecha").getValue();
                    String titulo = (String) childDataSnapshot.child("Titulo").getValue();
                    String descripcion = (String) childDataSnapshot.child("Descripcion").getValue();
                    String correo = (String) childDataSnapshot.child("Correo").getValue();
                    String nodo = childDataSnapshot.getKey(); //inserta el nombre del nodo para luego poder buscarlo y borrarlo

                    MensajesIncidencias mi = new MensajesIncidencias(equipo, fecha, titulo, descripcion, correo, nodo);
                    datos2.add(mi);

                }

                Mensajes.muestraIncidencias(datos2, ctx);
                //se pasa como parametro el contexto que se recibe
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
              //  Log.d(TAGLOG, "<-------- Error DBmensajes listaMensajes ------>", databaseError.toException());
            }

        };

        dbQuery.addValueEventListener(eventListener);

//        Log.d(TAGLOG, "----------------------- pasando2---------------" + datos.size());

    }

    public void borrar(String nodo){

        dbMensajes.child(nodo).removeValue();
 Log.d(TAGLOG, "----------------------- nodooooo---------------" + nodo);
    }

}


