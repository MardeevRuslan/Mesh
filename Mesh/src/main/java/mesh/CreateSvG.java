package mesh;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateSvG {

    public void create() throws TransformerException, IOException {

        String outputFilePath = "target/resources/mesh.svg";

        Mesh mesh = new Mesh();
        mesh.calculationMesh();

        int radius = 100;
        String fill = "green";

        // Create a new SVG document
        DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = "http://www.w3.org/2000/svg";
        SVGDocument document = (SVGDocument) domImpl.createDocument(svgNS, "svg", null);
        int svgWidth = mesh.getWidth();
        int svgHeight = mesh.getLength();
        Element svgRootElement = document.getDocumentElement();
        svgRootElement.setAttributeNS(null, "width", String.valueOf(svgWidth) + radius);
        svgRootElement.setAttributeNS(null, "height", String.valueOf(svgHeight) + radius);

        // Add circles in rows


        int distanceX = mesh.getStepLength();
        int distanceY = mesh.getStepWidth();
        int rows = mesh.getCountStepLength();
        int columns = mesh.getCountStepWidth();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int cx = radius + (j * distanceX);
                int cy = radius + (i * distanceY);
                Element circle = createCircle(document, cx, cy, radius, fill);
                document.getDocumentElement().appendChild(circle);

            }
        }

        // Save the SVG document to a file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new FileOutputStream(outputFilePath));
        transformer.transform(source, result);

        System.out.println("New SVG file created: " + outputFilePath);

        // Open the created SVG file
        File svgFile = new File(outputFilePath);
        Desktop.getDesktop().open(svgFile);
    }

    private static Element createCircle(SVGDocument document, int cx, int cy, int r, String fill) {
        String svgNS = "http://www.w3.org/2000/svg";
        Element circle = document.createElementNS(svgNS, "circle");
        circle.setAttributeNS(null, "cx", String.valueOf(cx));
        circle.setAttributeNS(null, "cy", String.valueOf(cy));
        circle.setAttributeNS(null, "r", String.valueOf(r));
        circle.setAttributeNS(null, "fill", fill);
        return circle;
    }

}


