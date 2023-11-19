package mesh.app;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.util.SVGConstants;
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

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, TransformerException {
        String sourceFilePath = "target/resources/example.svg";
        String outputFilePath = "target/resources/mesh.svg";

        // Создание нового документа SVG
        DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGConstants.SVG_NAMESPACE_URI;
        SVGDocument document = (SVGDocument) domImpl.createDocument(svgNS, "svg", null);

        // Загрузка исходного SVG-изображения
        Element sourceElement = document.createElementNS(svgNS, "image");
        sourceElement.setAttributeNS(svgNS, "href", sourceFilePath);
        document.getDocumentElement().appendChild(sourceElement);

        // Сохранение нового SVG-документа
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new FileOutputStream(outputFilePath));
        transformer.transform(source, result);

        System.out.println("Новый файл SVG успешно создан: " + outputFilePath);

        // Открытие созданного файла SVG
        File svgFile = new File(outputFilePath);
        Desktop.getDesktop().open(svgFile);
    }
}
//        String sourceFilePath = "target/resources/example.svg";
//        String outputFilePath = "target/resources/mesh.svg";
//
//        // Создание нового документа SVG
//        DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
//        String svgNS = SVGConstants.SVG_NAMESPACE_URI;
//        SVGDocument document = (SVGDocument) domImpl.createDocument(svgNS, "svg", null);
//
//        // Получение корневого элемента SVG
//        SVGSVGElement root = document.getRootElement();
//
//        // Загрузка исходного SVG-изображения
//        Element sourceElement = document.createElementNS(svgNS, "image");
//        sourceElement.setAttributeNS(svgNS, "href", sourceFilePath);
//        root.appendChild(sourceElement);
//
//        // Получение ограничивающего прямоугольника исходного изображения
//        SVGElement sourceImage = (SVGElement) sourceElement;
//        String widthStr = sourceImage.getAttribute("width");
//        String heightStr = sourceImage.getAttribute("height");
//
//        double width = 0;
//        double height = 0;
//
//        if (!widthStr.isEmpty() && !heightStr.isEmpty()) {
//            width = Double.parseDouble(widthStr);
//            height = Double.parseDouble(heightStr);
//        }
//
//        // Создание трансформации для размещения изображения дважды на расстоянии 10 см
//        AffineTransform transform = AffineTransform.getTranslateInstance(10, 0);
//
//        // Копирование исходного изображения и добавление его в документ со сдвигом
//        SVGUseElement useElement = (SVGUseElement) document.createElementNS(svgNS, "use");
//        useElement.setAttributeNS(XLINK_NAMESPACE_URI, "href", "#" + sourceImage.getAttribute("id"));
//        useElement.setAttributeNS(SVGConstants.XLINK_NAMESPACE_URI, "x", String.valueOf(width + 10));
//        useElement.setAttributeNS(svgNS, "y", "0");
//
//        // Применение трансформации ко второму изображению
//        String transformValue = "translate(10, 0)";
//        useElement.setAttribute("transform", transformValue);
//
//        // Добавление второго изображения в документ
//        root.appendChild(useElement);
//
//        // Сохранение нового SVG-документа
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        Transformer transformer = transformerFactory.newTransformer();
//        DOMSource source = new DOMSource(document);
//        StreamResult result = new StreamResult(new FileOutputStream(outputFilePath));
//        transformer.transform(source, result);
//
//        System.out.println("Новый файл SVG успешно создан: " + outputFilePath);
//    }

