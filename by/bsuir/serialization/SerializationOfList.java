package by.bsuir.serialization;

import java.io.*;
import java.util.List;

/**
 * Created by Koshelek on 04.04.2017.
 */
public class SerializationOfList<T> {
    public void SaveToFileList(List<T> savelist, String filename) throws IOException{
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(fos);
            oos.writeInt(savelist.size());
            for (T elem : savelist) {
                oos.writeObject(elem);
            }
        } finally {
            if (oos != null) {
                oos.close();
            }
        }
    }

    public void LoadFromFile(List<T> loadlist, String filename)throws IOException,ClassNotFoundException,ClassCastException{
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
            int count = ois.readInt();
            for(int i = 0; i<count;i++)
            {
                T loadObject = (T) ois.readObject();
                loadlist.add(loadObject);
            }
        } finally {
            if (ois != null) {
                ois.close();
            }
        }
    }
}
