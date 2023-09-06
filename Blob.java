import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Scanner;

public class Blob
{
    private String myString;

    public void blobify (String inputFileName, String objectsPath) throws NoSuchAlgorithmException, IOException
    {
        //writes new file into objects folder
        FileWriter writer = new FileWriter (new File (objectsPath + "/" + getSHA1String(inputFileName)));
        PrintWriter out = new PrintWriter(writer);
        out.println (myString);
        writer.close ();
        out.close ();
    }

    public String getSHA1String (String inputFileName) throws FileNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException
    {
        //reads file into String myString
        Scanner scanner = new Scanner(new File(inputFileName));
        myString = scanner.useDelimiter("\\A").next();
        scanner.close();
        //hashes file with SHA1 hash code into String called SHA1)
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(myString.getBytes("UTF-8"));
        Formatter formatter = new Formatter();
        for (byte b : crypt.digest())
        {
            formatter.format("%02x", b);
        }
        String SHA1 = formatter.toString();
        formatter.close();
        return SHA1;
    }
}