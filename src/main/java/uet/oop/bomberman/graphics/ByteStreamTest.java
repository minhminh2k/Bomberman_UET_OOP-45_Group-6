package uet.oop.bomberman.graphics;
import java.io.*;
public class ByteStreamTest
{
    public static void main(String[] args) throws IOException
    {
        FileInputStream sourceStream = null;
        FileOutputStream targetStream = null;

        try
        {
            //Xem user directory hiện tại
            //Nếu chạy trên Intellij thì màn hình hiện ra đường dẫn đến project hiện tại
            //Nếu chạy class này bằng giao diện dòng lệnh tại thư mục chứa các file .class thì output sẽ khác
            System.out.println(System.getProperty("user.dir"));


            //Đảm bảo tệp source.txt có trong user directory nếu sử dụng relative path
            //sourceStream = new FileInputStream("source.txt");
            sourceStream = new FileInputStream("res/textures/classic.png");


            //Tệp destination.txt sẽ xuất hiện trong thư mục user directory
            targetStream = new FileOutputStream ("destination.png");

            int temp;
            while ((temp = sourceStream.read()) != -1)
                targetStream.write((byte)temp);
        }
        finally
        {
            if (sourceStream != null){
                sourceStream.close();
            }
            if (targetStream != null){
                targetStream.close();
            }
        }
    }
}