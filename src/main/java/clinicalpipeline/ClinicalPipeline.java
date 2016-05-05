package clinicalpipeline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.util.InvalidXMLException;

public class ClinicalPipeline {

	private static AnalysisEngineDescription pipeline;
	private static AnalysisEngine ae;
	private static ClinicalPipeline pipelineProvider = new ClinicalPipeline();
	
    public static ClinicalPipeline getInstance(){
        return pipelineProvider;
    }

    public static AnalysisEngineDescription getPipeline(){
        return pipeline;
    }
	
    public static AnalysisEngine getAnalysisEngine(){
    	return ae;
    }
    
	public ClinicalPipeline(){
		try {
			pipeline = AnalysisEngineFactory.createEngineDescription(
					"/home/apoorv/dev/claims/cog-claims/claims-nlp/desc/ctakes-clinical-pipeline/desc/analysis_engine/AggregatePlaintextFastUMLSProcessor");
			ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
			ae = UIMAFramework.produceAnalysisEngine(pipeline, resMgr, null);
		} catch (InvalidXMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		
		ClinicalPipeline pipelineProvider = ClinicalPipeline.getInstance();
		AnalysisEngineDescription pipeline = pipelineProvider.getPipeline();
		ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
		List<String> typeList = Arrays.asList("ProcedureMention", "DiseaseMention", "SignSymptomMention");
		
		try {
			AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(pipeline, resMgr, null);
			JCas jcas = ae.newJCas();
			
			while(true){
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				try {
					String s = br.readLine();
					jcas.setDocumentText(s);
					ae.process(jcas);
//					System.out.println(jcas.getDocumentText());
//					XmiCasSerializer.serialize(jcas.getCas(), System.out);
					AnnotationIndex index = jcas.getAnnotationIndex();
					Iterator<Annotation> annIterator = index.iterator();
					while(annIterator.hasNext()){
						Annotation ann = annIterator.next();
						if(typeList.contains(ann.getType().getShortName()))
							System.out.println(ann.getType().getShortName() + ": " + ann.getCoveredText());
						
					}
					jcas.reset();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AnalysisEngineProcessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}