/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineReviewAnalytics;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.*;
import java.util.*;
/**
 *
 * @author amal
 */
public class FeatureExtractor {

    /**
     * @param args the command line arguments
     */
    static private HashMap sortByValue(HashMap map) {
                List list = new LinkedList(map.entrySet());
                Collections.sort(list, new Comparator() {
                        @Override
                    public int compare(Object o1, Object o2) {
                        return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
                    }
                });

                HashMap result = new LinkedHashMap();
                for (Iterator it = list.iterator(); it.hasNext();) {
                    Map.Entry entry = (Map.Entry)it.next();
                    result.put(entry.getKey(), entry.getValue());
                }
                return result;
    } 
    public void extractFeature(String taggerPath, String inputPath, String outputPath, String featurePath) throws IOException, ClassNotFoundException {
            String line;
            MaxentTagger tagger = new MaxentTagger(taggerPath);
                // The sample string
            String tagged;
            BufferedWriter bw = new BufferedWriter (new FileWriter(outputPath)); // add true as 2nd param for appending
            BufferedReader br = new BufferedReader(new FileReader(inputPath));
            HashMap hashMap = new HashMap();
            while ((line = br.readLine())!= null){
                    //data is read from input and tagged and written to output
                    tagged = tagger.tagString(line);
                    bw.write(tagged+"\n");
                    StringTokenizer st = new StringTokenizer (tagged," ");
                    while(st.hasMoreTokens()){
                            String token = st.nextToken();
                            if((token.indexOf("/NN"))!= -1){
                                    //identifying and printing the token
                                    //code to hash goes here
                                    if(hashMap.containsKey(token.toLowerCase() ) ){
                                        Integer val = (Integer) hashMap.get(token.toLowerCase());
                                        val++;
                                        hashMap.put(token.toLowerCase(), new Integer(val));

                                    }
                                    else{
                                        hashMap.put(token.toLowerCase(), new Integer(1));
                                    }
                                // System.out.println(token);
                            }
                    }
            }
            /* now nouns have been tagged and hashed 
            * Next step is to sort the hashmap */

            //System.out.println(hashMap);
            br.close();
            bw.close();
            hashMap=sortByValue(hashMap);
                    
            /* the isolated features are to be written to a features file. Further stages of the analyis will read
            features from the file to use them. */
            
            BufferedWriter fw = new BufferedWriter (new FileWriter(featurePath));
            
            System.out.println("\n\n\nPopular Aspects of the product:\n\n");
            
            Iterator<Map.Entry<String, Integer>> entries = hashMap.entrySet().iterator();
            while (entries.hasNext()) {
                    Map.Entry<String, Integer> entry = entries.next();

                    // fix threshhold here
                    if( entry.getValue() >= 40	){ 
                            System.out.println( entry.getKey() + ", Value = " + entry.getValue());
                            fw.write(entry.getKey()+"\n");
                    }
            }
            fw.close();     
            
            
    }
        
}