package OnlineReviewAnalytics;
import java.io.*;
import java.util.HashMap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * JJ - Adjective
JJR - Adjective, comparative
JJS - Adjective, superlative
NN - Noun, singular or mass
NNS - Noun, plural
NNP - Proper noun, singular
NNPS - Proper noun, plural
RB - Adverb
RBR - Adverb, comparative
RBS - Adverb, superlative
VB - Verb, base form
VBD - Verb, past tense
VBG - Verb, gerund or present participle
VBN - Verb, past participle
VBP - Verb, non-3rd person singular present
VBZ - Verb, 3rd person singular present
 
 * */

/**
 *
 * @author amal
 */
public class ScoreAssigner {
    
    public void assign ( String inputPath, String outputPath) throws FileNotFoundException, IOException{
                    BufferedReader ir = new BufferedReader(new FileReader(inputPath));
                   // BufferedWriter ow = new BufferedWriter (new FileWriter ((outputPath)));
                    String line,word;
                    char POS;
                    double senti_score, total_senti_score=0;
                    HashMap<String,Character> h = new HashMap();
                    h.put("JJ",'a');
                    h.put("JJR",'a');
                    h.put("JJS",'a');
                    h.put("NN",'n');
                    h.put("NNS",'n');
                    h.put("NNP",'n');
                    h.put("NNPS",'n');
                    h.put("RB",'r');
                    h.put("RBR",'r');
                    h.put("RBS",'r');
                    h.put("VB",'v');
                    h.put("VBD",'r');
                    h.put("VBG",'v');
                    h.put("VBN",'v');
                    h.put("VBP",'v');
                    h.put("VBZ",'v');
                    SentimentClass obj=new SentimentClass();
                    obj.Load();
                    int index=0;
                     while ((line = ir.readLine())!= null){
                                total_senti_score = 0;
                                String[] words = line.split(" "); 
                                int len = words.length;
                                
                                for(int ctr=0;ctr<len;ctr++){
                                    if(words[ctr].length()==0)
                                        continue;
                                        //System.out.println("-"+words[ctr]+"-");
                                        word=(words[ctr].split("/"))[0];
                                        if ((words[ctr].split("/")).length<2)
                                            continue;
                                        if(h.containsKey((words[ctr].split("/"))[1])){
                                                POS = h.get((words[ctr].split("/"))[1]);
                                                senti_score=obj.getSentimentValue(word, POS);
                                                total_senti_score += senti_score;
                                        }
                                }
                                index++;
                                //index+ " * " + 
                               System.out.println(total_senti_score); 
                     }
    }
    
}
