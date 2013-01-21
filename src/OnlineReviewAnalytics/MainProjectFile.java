/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineReviewAnalytics;

/**
 *
 * @author amal
 */

//import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.IOException;
public class MainProjectFile {
                
                public static void main(String [] args)throws IOException, ClassNotFoundException{
                                
                      String taggerPath, inputPath, outputPath, featurePath;
                      taggerPath="/home/amal/NetBeansProjects/OnlineReviewAnalytics/amal-tagger/wsj-0-18-left3words.tagger";
                      inputPath="amal-data/amal";
                      outputPath="amal-data/output";
                      featurePath="amal-data/features";
                      
                     // MaxentTagger tagger = new MaxentTagger(taggerPath);
                // The sample string
                     
                      FeatureExtractor fe = new FeatureExtractor();
                      fe.extractFeature(taggerPath,inputPath,outputPath,featurePath);
                                
                      SentenceExtractor s = new SentenceExtractor ();
                      s.extractSentences();
                                
                                
                      //Sentiment Class demo
                      System.out.println("\n\nSentiment Values Demo:\n\n");
                      SentimentClass obj=new SentimentClass();
                      obj.Load();
                      double kk=obj.getSentimentValue("hope", 'v');
                      
                      new ScoreAssigner().assign("amal-data/battery@nn","");
                      //double kl=obj.getSentimentValue("sweet#2", 'a');
                      System.out.println(kk);
                      //System.out.println(kl);
                }
    
}
