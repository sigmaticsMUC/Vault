package vault.factory;

import scala.collection.mutable.ListBuffer;
import vault.core.Box;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 15.11.17.
 */
public class BoxFactory {

    private static final int BOX_LENGTH = 1000;


    public static List<Box> createBoxes(String path) {
        List<Box> boxes = new ArrayList<>();
        byte[] byteBuffer;
        try {
            byte[] byteArray = Files.readAllBytes(Paths.get(path));
            for(int i = 0; i < byteArray.length; i++) {
                int jj = 0;
                if(byteArray.length - i > BOX_LENGTH) {
                    byteBuffer = new byte[BOX_LENGTH];
                }
                else {
                    byteBuffer = new byte[byteArray.length - i];
                }
                for(int j = 0; j < BOX_LENGTH; j++) {
                    if(i+j < byteArray.length) {
                        byteBuffer[j] = byteArray[j+i];
                        jj = j;
                    }
                    else {
                        break;
                    }
                }
                boxes.add(new Box(i, byteBuffer));
                i += jj;
            }
            return boxes;
        }
        catch (IOException ex) {
            return null;
        }

    }
}
