package Utils;

import java.io.*;
import java.util.ArrayList;

public class SerializationUtils<T> {
    /**
     * Metodo que serializa una lista de objetos, "sobreescribe" un archivo para agregarle nuevos Objetos
     *
     * @param objects  recive el array de objetos con los objetos a serializar
     * @param fileName recive el nombre del archivo
     * @throws Exception Es necesario
     */
    public void serialize(ArrayList<T> objects, String fileName) throws Exception {
        try (FileOutputStream file = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(file)) {
            //sobreescribimos el archivo con todos los objetos en total. una iteracion por cada objeto
            for (T object : objects) {
                oos.writeObject(object);
            }
        } catch (Exception e) {
            System.out.println("Error en serializar");
        }
    }

    /**
     * Metodo para deserializar un archivo y convertirlo a objetos
     *
     * @param fileName recive el nombre del archivo a deserializar
     * @return devuelve el objeto generico
     * @throws Exception es utilizado para avisar al programa que el metodo puede lanzar varias excepciones
     */
    public ArrayList<T> deserialize(String fileName) throws Exception {
        ArrayList<T> objects = new ArrayList<>();
        //estemos creando un objeto de tipo archivo, con el nombre 'X'.
        //posteriormente estaremos creando una instancia del opbjeto ObjectInput.. el cual leera los objetos del archivo
        try (FileInputStream file = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(file)) {
            //leera el archivo repetidamente. y cuando queden 0 bytes por leer finalizara
            while (file.available() > 0) {
                T object = (T) ois.readObject();
                objects.add(object);
            }
        } catch (Exception e) {
            System.out.println("Error en deserializar"+ e);
        }
        return objects;
    }
}