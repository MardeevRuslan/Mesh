package mesh.app;

import mesh.CreateSvG;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, TransformerException {
        CreateSvG createSvG = new CreateSvG();
        createSvG.create();
    }
}


