package com.example.proyectomovil;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class APIArchivo {

    private String archivoUsuarios = "basura.txt";
    private Context context;
    private OutputStreamWriter output;
    private InputStreamReader input;

    public APIArchivo(Context context)
    {
        this.context = context;
    }

    public Usuario POST_Usuario(Usuario usuario, Context context)
    {
        try
        {
            output = new OutputStreamWriter(
                    context.openFileOutput(archivoUsuarios, Activity.MODE_PRIVATE));
            String contenido = ObtenerContenido(context);
            contenido = contenido + usuario.toString();
            output.write(contenido);
            output.flush();
            output.close();
            return usuario;
        }
        catch (IOException e) {
            return new Usuario("", "", -1, "", "");
        }
    }

    public Usuario GET_Usuario_Telefono(String telefono, Context context)
    {
        Usuario resultado = new Usuario();
        try
        {
            input = new InputStreamReader(context.openFileInput(archivoUsuarios));
            BufferedReader reader = new BufferedReader(input);
            String linea = reader.readLine();
            while (linea != null)
            {
                if (linea.equals(telefono))
                {
                    linea = reader.readLine();
                    String contrasena = linea.replace("***", "");
                    linea = reader.readLine();
                    String nombre = linea.replace("nombre:", "");
                    linea = reader.readLine();
                    String correo = linea.replace("correo:", "");
                    linea = reader.readLine();
                    String direccion = linea.replace("direccion:","");

                    resultado = new Usuario();
                    resultado.setTelefono(Integer.parseInt(telefono));
                    resultado.setContraseña(linea);
                    resultado.setNombre(nombre);
                    resultado.setCorreo(correo);
                    resultado.setDireccion(direccion);

                    reader.close();
                    return resultado;
                }
                linea = reader.readLine();
            }
            reader.close();
            input.close();
            resultado.setNombre("NOTFOUND");
            return resultado;
        }
        catch (Exception e) {
            Log.d("errores",e.toString());
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            return new Usuario("", "", -1, "", "");
        }
    }

    public Usuario GET_Usuario(String telefono, String contrasena, Context context)
    {
        Usuario resultado = new Usuario();
        try
        {
            input = new InputStreamReader(context.openFileInput(archivoUsuarios));
            BufferedReader reader = new BufferedReader(input);
            String linea = reader.readLine();
            while (linea != null)
            {
                if (linea.equals(telefono))
                {
                    linea = reader.readLine();
                    String pass = "***" + contrasena;
                    resultado = new Usuario();
                    if (pass.equals(linea))
                    {
                        resultado.setTelefono(Integer.parseInt(telefono));
                        resultado.setContraseña(contrasena);
                        linea = reader.readLine();
                        String nombre = linea.replace("nombre:", "");
                        linea = reader.readLine();
                        String correo = linea.replace("correo:", "");
                        linea = reader.readLine();
                        String direccion = linea.replace("direccion:","");
                        resultado.setNombre(nombre);
                        resultado.setCorreo(correo);
                        resultado.setDireccion(direccion);

                        reader.close();
                        return resultado;
                    }
                    else
                    {
                        resultado.setNombre("ERROR");
                        return resultado;
                    }
                }
                linea = reader.readLine();
            }
            reader.close();
            input.close();
            resultado.setNombre("NOTFOUND");
            return resultado;
        }
        catch (FileNotFoundException e)
        {
            return new Usuario("NOTFOUND", "", -1, "", "");
        }
        catch (Exception e) {
            Log.d("errores",e.toString());
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            return new Usuario("", "", -1, "", "");
        }
    }

    private String ObtenerContenido(Context context) {
        try
        {
            input = new InputStreamReader(context.openFileInput(archivoUsuarios));
            BufferedReader reader = new BufferedReader(input);
            String linea = reader.readLine();
            String contenido = "";
            while (linea != null)
            {
                contenido = contenido + linea + "\n";
                linea = reader.readLine();
            }
            reader.close();
            input.close();
            return contenido;
        }
        catch (Exception e) {}
        return "";
    }
}
