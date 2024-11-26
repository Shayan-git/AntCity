import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.InflaterInputStream;

public class Decoder {
//    Decode Flater Stream
    private void decodeFile() {
        int data;
        List<Integer> integerList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("order.txt");
            InflaterInputStream iis = new InflaterInputStream(fis);
            while((data=iis.read()) != -1)
                integerList.add(data);
            iis.close();
            fis.close();

            PrintWriter out = new PrintWriter("helper.txt");
            out.close();
            FileOutputStream fos = new FileOutputStream("helper.txt");
            for (int a : integerList)
                fos.write(a);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    Manage helper.txt
    private String[] getArrayFromHelperFile(int row) {
        File file = new File("helper.txt");
        String s[] = new String[row];
        int cS = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                s[cS++] = scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return s;
    }

    private void deleteHelperFile() {
        File myObj = new File("helper.txt");
        myObj.delete();
    }

//    Decode Caesars Cipher
    private String decodeString(String data, int shift) {
        shift = 26 - shift;
        String encodedStr = "";
        for (char c : data.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                encodedStr += ((char) ((c - base + shift) % 26 + base));
            } else
                encodedStr += (c);
        }
        return encodedStr;
    }

//    Decode order.txt and return the string
    public String decodeOrderFile(int sizeOfQueue) {
        decodeFile();

        String[] fileStringArray = getArrayFromHelperFile(sizeOfQueue);

        deleteHelperFile();

        String str = "";
        for (String s : fileStringArray)
            str += decodeString(s, 3) + "\n";
        return str;
    }
}
