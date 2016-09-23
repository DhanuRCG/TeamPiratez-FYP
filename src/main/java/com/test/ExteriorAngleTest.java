package com.test;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.sample.Angle;
import com.sample.GeoRelation;
import com.sample.Line;
import com.sample.Point;
import com.sample.Relation;
import com.sample.Triangle;

/**
 * This is a sample class to launch a rule.
 */
public class ExteriorAngleTest {

	// load up the knowledge base
    static KieServices ks = KieServices.Factory.get();
    static KieContainer kContainer = ks.getKieClasspathContainer();
	static KieSession kSession = kContainer.newKieSession("ksession-rules");
    public static final void main(String[] args) {
        try {

            	Point point1 = new Point('A');
            	Point point2 = new Point('B');
            	Point point3 = new Point('C');        	
            	Point point4 = new Point('D');
            	//Point pointO = new Point('M');
      
            	Line lineAB = new Line(point1, point2);
            	Line lineBC = new Line(point2, point3);
            	Line lineCA = new Line(point3, point1);
            	Line lineBD = new Line(point2, point4);
            	Line lineCD = new Line(point3,point4);
            	
            	Angle angleABC = new Angle(lineAB,lineBC);
            	Angle angleBCA = new Angle(lineBC,lineCA);
            	Angle angleCAB = new Angle(lineCA,lineAB);
            	
            	GeoRelation ABeqAC = new GeoRelation(lineAB, lineCA, Relation.EQUALS);
            	GeoRelation ABeqBC = new GeoRelation(lineAB, lineBC, Relation.EQUALS);
            	GeoRelation ConBD = new GeoRelation(lineBD, point3, Relation.ON_THE_LINE);
            	Triangle triangleABC = new Triangle(point1,point2,point3);
            	/*
            	GeoRelation ABparrCD = new GeoRelation(lineAB, lineCD, Relation.PARALLEL_LINES);
            	GeoRelation OonAB = new GeoRelation(lineAB, pointO, Relation.ON_THE_LINE);
            	GeoRelation OonCD = new GeoRelation(lineCD, pointO, Relation.ON_THE_LINE);
            	GeoRelation AMeqMD = new GeoRelation(lineAM, lineMD, Relation.EQUALS);
            	GeoRelation angleEQ = new GeoRelation(angleAMB, angleCMD, Relation.EQUALS);
            	
            	
            	Triangle triangleABM = new Triangle(point1, point2, pointO);
            	Triangle triangleCMD = new Triangle(point3, point4, pointO);*/
            	
            	

            	kSession.insert(point1);
            	kSession.insert(point2);
            	kSession.insert(point3);
            	kSession.insert(point4);
                kSession.insert(lineAB);
                kSession.insert(lineBC);
                kSession.insert(lineCA);
                kSession.insert(lineBD);
                kSession.insert(lineCD);
                kSession.insert(angleABC);
                kSession.insert(angleBCA);
                kSession.insert(angleCAB);
                kSession.insert(ABeqAC);
                kSession.insert(ABeqBC);
                kSession.insert(ConBD);
                kSession.insert(triangleABC);
                //kSession.insert(triangleCMD);
                            
                kSession.fireAllRules();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static List<Triangle> check(){
    	QueryResults results = kSession.getQueryResults( "getObjectsOfTriangle" ); 
    	List<Triangle> triangles = new ArrayList<Triangle>();
    	for ( QueryResultsRow row : results ) {
       		Triangle triangle = ( Triangle ) row.get( "$result" ); //you can retrieve all the bounded variables here
   	   		//do whatever you want with Triangle
       		triangles.add(triangle);
   		}
    	return triangles;
 			
    }
    

}
