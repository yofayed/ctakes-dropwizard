package app.resources;


import javax.ws.rs.*;

import com.codahale.metrics.annotation.Timed;
import javax.ws.rs.core.MediaType;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.XMLSerializer;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.atomic.AtomicLong;
import clinicalpipeline.ClinicalPipeline;
import app.resources.StringOutputStream;

@Path("/annotate")
@Produces(MediaType.APPLICATION_XML)
public class AppResource {

    private final String defaultFunction;
    private final AtomicLong counter;
    private static AnalysisEngine ae;
    
    public AppResource(String defaultFunction) {
        this.defaultFunction = defaultFunction;
        this.counter = new AtomicLong();
        ae = ClinicalPipeline.getInstance().getAnalysisEngine();
    }

    @POST
    @Timed
    public String annotate(RequestBody requestBody){

        String text = requestBody.getText();
        
//      annotate text here using analysis engine
        
        String xmlText;
        try {
			JCas jcas = ae.newJCas();
			jcas.setDocumentText(text);
			ae.process(jcas);
			StringOutputStream outstream = new StringOutputStream(); 
			XmiCasSerializer.serialize(jcas.getCas(), outstream);
			xmlText = outstream.getString();
			
	        return xmlText;
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AnalysisEngineProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "";
//        return new AppResponse(counter.incrementAndGet(), "");

    }

}