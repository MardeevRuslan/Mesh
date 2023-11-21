package mesh.svg;

import mesh.mesh.Mesh;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateSvG {

    private SVGDocument document;
    private final String outputFilePath = "./mesh.svg";
    private final String svgNS = "http://www.w3.org/2000/svg";
    private final int RADIUS = 100;

    private final int DIAMETER = 2 * RADIUS;
    private final String FILL_NONE = "none";

    private final String FILL_BLACK = "black";
    private final String FILL_WHITE = "white";
    private Mesh mesh;
    private final String STROKE = "black";
    private final String STROKE_WIDTH = "5";

    public CreateSvG(Mesh mesh) {
        this.mesh = mesh;
    }


    public void create() throws TransformerException, IOException {
        createSvgDocument();
        completionSvgDocument();
        saveSvgDocument();
        openSvgDocument();
    }


    private void createSvgDocument() {
        DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
        this.document = (SVGDocument) domImpl.createDocument(svgNS, "svg", null);
        int svgWidth = mesh.getWidth() + DIAMETER;
        int svgHeight = mesh.getLength() + DIAMETER;
        Element svgRootElement = document.getDocumentElement();
        svgRootElement.setAttributeNS(null, "width", String.valueOf(svgWidth) );
        svgRootElement.setAttributeNS(null, "height", String.valueOf(svgHeight) );
    }

    private void completionSvgDocument() {
        int distanceX = mesh.getStepLength();
        int distanceY = mesh.getStepWidth();
        int rows = mesh.getCountStepLength();
        int columns = mesh.getCountStepWidth();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int cx = RADIUS + (j * distanceX);
                int cy = RADIUS + (i * distanceY);
                appendCircle(cx, cy );
                appendRectangleVertically(cx, cy );
                appendRectangleHorizontally(cx, cy);
            }
        }
    }

    private void saveSvgDocument() throws TransformerException, FileNotFoundException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new FileOutputStream(outputFilePath));
        transformer.transform(source, result);
    }

    private void openSvgDocument() throws IOException {
        File svgFile = new File(outputFilePath);
        Desktop.getDesktop().open(svgFile);
    }

    private Element createCircle(int cx, int cy, int r, String fill) {
        String svgNS = "http://www.w3.org/2000/svg";
        Element circle = this.document.createElementNS(svgNS, "circle");
        circle.setAttributeNS(null, "cx", String.valueOf(cx));
        circle.setAttributeNS(null, "cy", String.valueOf(cy));
        circle.setAttributeNS(null, "r", String.valueOf(r));
        circle.setAttributeNS(null, "fill", fill);
        circle.setAttributeNS(null, "stroke", STROKE);
        circle.setAttributeNS(null, "stroke-width", STROKE_WIDTH);
        return circle;
    }


    private Element createRectangle(int x, int y, int width, int height) {
        String svgNS = "http://www.w3.org/2000/svg";
        Element rectangle = this.document.createElementNS(svgNS, "rect");
        rectangle.setAttributeNS(null, "x", String.valueOf(x));
        rectangle.setAttributeNS(null, "y", String.valueOf(y));
        rectangle.setAttributeNS(null, "width", String.valueOf(width));
        rectangle.setAttributeNS(null, "height", String.valueOf(height));
        rectangle.setAttributeNS(null, "fill", FILL_WHITE);
        rectangle.setAttributeNS(null, "stroke", FILL_WHITE);
        rectangle.setAttributeNS(null, "stroke-width", STROKE_WIDTH);
        return rectangle;
    }

    private void appendElement(Element element) {
        document.getDocumentElement().appendChild(element);
    }

    private void appendCircle(int cx, int  cy ) {
        for (int radius = RADIUS; radius > 20; radius -= 20) {
            Element circle = createCircle(cx, cy, radius, FILL_NONE);
            appendElement(circle);
        }
        Element circle = createCircle(cx, cy, RADIUS - 60, FILL_BLACK);
        appendElement(circle);
    }


    private void appendRectangleVertically(int cx, int cy ) {
        int rectangleWidth = 4;
        int rectangleHeight = DIAMETER;
        int rectangleX = cx - (rectangleWidth / 2);
        int rectangleY = cy - RADIUS;
        Element rectangle = createRectangle(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
        appendElement(rectangle);
    }

    private void appendRectangleHorizontally(int cx, int cy ) {
        int rectangleWidth = DIAMETER;
        int  rectangleHeight = 2;
        int  rectangleX = cx - RADIUS;
        int rectangleY = cy - (rectangleHeight / 2);
        Element rectangle = createRectangle(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
        appendElement(rectangle);
    }

}


