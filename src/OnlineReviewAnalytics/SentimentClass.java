/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineReviewAnalytics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author amal
 */
public class SentimentClass {
          String pathToSWN = "/home/amal/NetBeansProjects/OnlineReviewAnalytics/amal-tools/SentiWordNet3.0.txt";
          String line;
          HashMap hashMap = new HashMap();
          public void Load() throws FileNotFoundException, IOException{
                /**
               * This function appends the POS character to the the end of the key which is of the form nascent#1, 
               * The final key becomes for instance, nascent#1a etc
               */
                BufferedReader br = new BufferedReader(new FileReader(pathToSWN));
                while((line = br.readLine()) != null){
                             String[] data = line.split("\t");
			     Double score = Double.parseDouble(data[2])-Double.parseDouble(data[3]);
                             String pos_String=data[0];
                             Character pos = pos_String.charAt(0);
                             String[] words = data[4].split(" ");
                             for(String w:words){
                                        w=w+pos;
                                        hashMap.put(w, score);
                             }
                             
                }
          }
          public double getSentimentValue(String wordParam, char POS) {
           /**
            * Function returns the avg sentiment across all senses of the passed word and part of speech. 
            * If word is not present in the hashMap, the sentiment is returned as 0
            * Sample function call: double val = getSentimentValue("infinite#4",'a');
            */            
                       double score,score_total=0,score_average=0;
                       int sense=1,occurance=0;
                       String word;
                       word=wordParam;
                       word = word + "#" + sense;
                       //System.out.println(word);
                       word=word+POS;
                       if(hashMap.containsKey(word)){
                               for (int ctr=1; ctr<=10;ctr++){
                                   if (hashMap.containsKey(word)){
                                        score=(Double)hashMap.get(word);
                                        word=wordParam;
                                        word += "#" + ctr;
                                        word=word+POS;
                                        //System.out.println(score+"-"+word);
                                        occurance++;
                                        score_total = score_total + score;
                                        score_average = score_total /occurance;
                                    }
                                }
                               
                                //System.out.println("Total score="+score_total);
                                //System.out.println("Total avg="+score_average);
                                return score_average;
                       }
                       else return 0;
          }        
    
}