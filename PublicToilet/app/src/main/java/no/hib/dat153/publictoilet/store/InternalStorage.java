package no.hib.dat153.publictoilet.store;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalStorage {
    public static String FILENAME = "dokart.json";
    private Context context;


    public InternalStorage(Context context) {
        this.context = context;
    }

    // DONE man kan skrive til xml filer som vanlige filer.
    public boolean writeFile(String s) {
        boolean success = false;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(FILENAME, context.MODE_PRIVATE);
            fileOutputStream.write(s.getBytes());
            fileOutputStream.close();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return success;
    }

    // Skjekker om filen eksisterer.
    public Boolean fileExsist() {
        File file = context.getFileStreamPath(FILENAME);
        return file.exists();


    }


    public String readJsonFile(){
        String retur = "";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fileInputStream);
            char[] inputBuffer = new char[fileInputStream.available()]; // allocate space
            isr.read(inputBuffer);
            retur = new String(inputBuffer);
            fileInputStream.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retur;
    }

}


