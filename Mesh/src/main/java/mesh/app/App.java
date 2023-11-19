package mesh.app;

import mesh.CreateSvG;

import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, TransformerException {
        CreateSvG createSvG = new CreateSvG();
        createSvG.create();
    }
}


//        String sourceFilePath = "target/resources/example.svg";
//        Element sourceElement = document.createElementNS(svgNS, "image");
//        sourceElement.setAttributeNS(svgNS, "href", sourceFilePath);
//        document.getDocumentElement().appendChild(sourceElement);
