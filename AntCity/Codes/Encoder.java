import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DeflaterOutputStream;

public class Encoder {
    private final List<Queen> queenList;
    private final List<Worker> workerList;
    private final List<String> childrenList;
    private final Queue<String> childrenQueue;

    public Encoder() {
        this.queenList = new ArrayList<>();
        this.workerList = new ArrayList<>();
        this.childrenList = new ArrayList<>();
        this.childrenQueue = new Queue<>();

        try {
            PrintWriter out = new PrintWriter("order.txt");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String> getChildrenList() {
        return childrenList;
    }

    public Queue<String> getChildrenQueue() {
        return childrenQueue;
    }

    //    Add parents.txt to Queen and Worker lists
    public void addParentsFileToLists(String fileName) {
        File file = new File(fileName);
        String s = "";
        String temp = "";
        int numberOfQueens = 0;
        int numberOfWorkers = 0;
        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                s = scanner.nextLine();
                for (int i = 0; i < s.length(); i++) {
                    if (Character.isDigit(s.charAt(i)))
                        temp += s.charAt(i);
                    else {
                        numberOfQueens = Integer.parseInt(temp);
                        temp = "";
                    }
                }
                numberOfWorkers = Integer.parseInt(temp);
            }

            for (int i = 0; i < numberOfQueens; i++)
                if (scanner.hasNextLine())
                    queenList.add(new Queen(scanner.nextLine()));

            for (int i = 0; i < numberOfWorkers; i++)
                if (scanner.hasNextLine())
                    workerList.add(new Worker(scanner.nextLine()));

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    Calculate children from Queen and Worker lists
    public void calculateChildren() {
        List<String> queenPrefixesList;
        List<String> workerSubstringsList;

        for (Queen queen : queenList) {
            queenPrefixesList = queen.getPrefixes();
            for (Worker worker : workerList) {
                workerSubstringsList = worker.getSubstrings();

                for (String strQueen : queenPrefixesList)
                    for (String strWorker : workerSubstringsList)
                        if (strQueen.length() == strWorker.length() && strQueen.equals(strWorker))
                            childrenList.add(strQueen);
            }
        }
    }

//    Manage order.txt
    private void setIntoOrderFile(String s) {
        try {
            PrintWriter out = new PrintWriter("order.txt");
            out.print(s);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getFromOrderFile() {
        File file = new File("order.txt");
        String s = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                if (s.equals(""))
                    s = scanner.nextLine();
                else
                    s += "\n" + scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return s;
    }

    public void appendRowToOrderFile(String newRow) {
        String s = getFromOrderFile();
        if (s.equals(""))
            s += newRow;
        else
            s += "\n" + newRow;
        setIntoOrderFile(s);
    }

//    Encode Caesars Cipher
    public String encodeString(String data, int shift) {
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

//    Encode Flater Stream
    public void encodeFile() {
        int data;
        List<Integer> integerList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("order.txt");
            while ((data=fis.read()) != -1)
                integerList.add(data);
            fis.close();

            FileOutputStream fos = new FileOutputStream("order.txt");
            DeflaterOutputStream dos = new DeflaterOutputStream(fos);
            for (int a : integerList)
                dos.write(a);
            dos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
