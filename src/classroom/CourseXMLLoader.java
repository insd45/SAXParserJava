/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroom;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author dale
 */
public class CourseXMLLoader {
    
    public static XMLNode load(File xmlCourseFile) throws Exception {
        XMLNode root = new XMLNode("Root");
        try {
            /*DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlCourseFile);
            doc.getDocumentElement().normalize();
            NodeList studentNodes = doc.getElementsByTagName("student");
            for (int index = 0; index < studentNodes.getLength(); index++) {
                Node studentNode = studentNodes.item(index);
                if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element studentElement = (Element) studentNode;
                    String idString = studentElement.getAttribute("id");
                    String pawprint = studentElement.getElementsByTagName("pawprint").item(0).getTextContent();
                    String firstName = studentElement.getElementsByTagName("firstname").item(0).getTextContent();
                    String lastName = studentElement.getElementsByTagName("lastname").item(0).getTextContent();
                    String gradeString = studentElement.getElementsByTagName("grade").item(0).getTextContent();
                    
                    int id = Integer.parseInt(idString);
                    int grade = Integer.parseInt(gradeString);
                    
                    Student student = new Student(id, pawprint, firstName,lastName, grade);
                    course.addStudent(student);
                    
                }
            }*/
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler studentHandler = new DefaultHandler() {
                /**
                * Event: Parser starts reading an element
                */
               boolean rootSet = false;
               XMLNode currentNode;
               XMLNode tempNode;
               ArrayList<XMLNode> stack = new ArrayList<XMLNode>();

               /*@Override
               public void startElement(String s1, String s2, String elementName, Attributes attributes) 
                       throws SAXException {
                   //print an element's name
                   System.out.print(elementName + ": ");
                   if(rootSet){
                       tempNode = new XMLNode(elementName);
                       currentNode.getChildren().add(tempNode);
                       currentNode = tempNode;
                   } else {
                       rootSet = true;
                       currentNode = new XMLNode(elementName);
                       root.getChildren().add(currentNode);
                   }
                   //print all attributes for this element
                   for(int i = 0; i < attributes.getLength(); i++) {
                       System.out.print("attribute: " + attributes.getValue(i));
                   }
               }*/
               
               @Override
               public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                   //print an element's name
                   System.out.print(qName + ": ");
                   if(rootSet){
                       tempNode = new XMLNode(qName);
                       currentNode.getChildren().add(tempNode);
                       currentNode = tempNode;
                       stack.add(currentNode);
                   } else {
                       rootSet = true;
                       /*currentNode = new XMLNode(qName);
                       root.getChildren().add(currentNode);*/
                       currentNode = root;
                       
                   }
                   //print all attributes for this element
                   for(int i = 0; i < attributes.getLength(); i++) {
                       System.out.print("attribute: " + attributes.getValue(i));
                   }
               }

               @Override
               public void characters(char ch[], int start, int length) throws SAXException {
                   System.out.print(new String(ch, start, length));
                   /*if(rootSet){
                       currentNode.setContent(new String(ch, start, length));
                   } else {
                       root.setContent(new String(ch, start, length));
                   }*/
                   
                   currentNode.setContent(new String(ch, start, length));
               }
                
            };
            saxParser.parse(xmlCourseFile, studentHandler);
            
      } catch (Exception e) {
         throw e;
      }
        
      return root; 
    }
}

class XMLNode {
    public static String qName;
    public static ArrayList<XMLNode> children = new ArrayList<XMLNode>();
    public static String content;
    
    public XMLNode(String name){
        this.qName = name;
    }
    
    public static ArrayList<XMLNode> getChildren(){
        return children;
    }
    
    public static String getContent(){
        return content;
    }
    
    public static void setContent(String cont){
        content = cont;
    }
    
    public static String getName(){
        return qName;
    }
}
