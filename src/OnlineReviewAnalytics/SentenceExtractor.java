/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineReviewAnalytics;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author amal
 */
public class SentenceExtractor {
             /**
              * The purpose of this class is to extract relevant sentences related to each product feature.
              * Each of these sentences will be stored in a separate file corresponding to that feature
              * The features will be read from the features file              
              *
              */
              public void extractSentences() throws IOException{
                     BufferedReader fr = new BufferedReader(new FileReader("amal-data/features"));
                     BufferedReader or = new BufferedReader(new FileReader("amal-data/output"));
                     ArrayList al = new ArrayList(); 
                     String line;
                     int len,ctr;
                     while ((line = fr.readLine())!= null){
                            al.add(line);
                     }
                     len = al.size();
                     Object featurelist[] = al.toArray(); //featurelist is the array containg all the features
                     while ((line = or.readLine())!= null){
                            String sentence[]=line.split("[.?!]");
                            int no_of_sentences = sentence.length;
                            //for each senetce, see if a feature occurs in it and write it to the correponding file
                            
                            for(int i=0;i<no_of_sentences;i++){
                                    //System.out.println(sentence[i]);
                                    for(ctr=0;ctr<len;ctr++){
                                           // System.out.println(featurelist[ctr].toString());
                                            
                                            BufferedWriter bw = new BufferedWriter (new FileWriter("amal-data/"+featurelist[ctr].toString().replace('/','@'),true));
                                            // this creates a new file for each of the features with the '/' in the filenames replaced by '@'
                                            if((sentence[i]).toLowerCase().indexOf(featurelist[ctr].toString())!=-1)  {
                                                bw.write(sentence[i]);
                                                bw.write("\n");
                                            }
                                            bw.close(); 
                                            
                                     }
                                   // System.out.println("..............");
                            }
                     }
              }
}
