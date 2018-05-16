
package proje3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import static java.nio.file.Files.list;
import java.nio.file.Paths;
import java.util.*;
import static java.util.Collections.list;

class sayac{
    int num;
    String ngram;
    public sayac(){}
    public sayac(int num, String ngram) {
        this.ngram = ngram;
        this.num = num;
    }
}
public class Proje3 {

    public static void main(String[] args) throws FileNotFoundException, IOException  {
 
  /*  File file = new File("deneme.txt");
    FileReader fileReader = new FileReader(file);  
  String line;
   BufferedReader br = new BufferedReader(fileReader);

       while ((line = br.readLine()) != null) {
   System.out.println(line); //dosya içi satır satır okuma denemesi
      }
   br.close();  
    
    StringBuilder birlesmisMetin = new StringBuilder();
   */
    int rnd = 0;
    File klasor1150haber = new File("1150haber");
    File[] list=klasor1150haber.listFiles(); 
   
  
 //  File kategori[] = new File("1150haber");
 // ArrayList <String> dosyaAdlari = new ArrayList <String> ();
  Map<String, String> dosyaAdlari = new HashMap<String, String>();
  Map<String, Integer> mapEkonomi = new HashMap<String,Integer>();
  Map<String, Integer> mapMagazin = new HashMap<String,Integer>();
  Map<String, Integer> mapSaglik = new HashMap<String,Integer>();
  Map<String, Integer> mapSiyasi = new HashMap<String,Integer>();
  Map<String, Integer> mapSpor = new HashMap<String,Integer>();
//dosya okuma: 
  for(int i=0;i<list.length;i++){
   // System.out.println(list[i].getName()+"---------------"); //klasör adlarını yazıyor
    if (list[i].isDirectory()) {
        for (File dosya:list[i].listFiles()){
         //   System.out.println(dosya.getName()); //alt klasörlerdeki dosyaların adlarını yazıyor
           //List<String> lines = Files.readAllLines(Paths.get(dosya.getName()), StandardCharsets.UTF_8);
            //String text = new String(Files.readAllBytes(Paths.get(dosya.getName())), StandardCharsets.UTF_8);
            // String icerik = new Scanner(dosya).useDelimiter("\\A").next();
            FileInputStream fis = new FileInputStream(dosya);
            byte[] data = new byte[(int) dosya.length()];
            fis.read(data);
            fis.close();
           String str = new String(data, "UTF-8");
    //n-gram oluşturma:       
           Vector v2 = new Vector();
        int m=2; //2-gram için 2
        for (int j=0;j<str.length()-m+1;j++) {
            String y = str.substring(j,j+m);
            boolean buldu2 = false;
            for(int h=0;h<v2.size();h++) {
                sayac s2= (sayac)v2.elementAt(h);
                if (y.equals(s2.ngram)){
                    buldu2=true;
                    s2.num++;
                }
            }
            if (!buldu2) 
                v2.add(new sayac(1,y));
            }
        rnd++;
        if (rnd%4!=1)
        {
            // metinlerin tamamının 3/4'ü kategorisiz olarak dict.'e atıldı:
        dosyaAdlari.put(dosya.getName(), str);
        }
        else {
            // test kümesini burada oluştur (1/4)
        }  
        for(int z=0; z<v2.size();z++){
            sayac s3= (sayac)v2.elementAt(z);
            if (s3.num>=50 && rnd%4!=1) 
            {
            //  System.out.println(s3.ngram+";"+s3.num);
                switch (list[i].getName()){
                        case "ekonomi": 
                            mapEkonomi.put(s3.ngram, s3.num);
                            break;
                        case "magazin": 
                            mapMagazin.put(s3.ngram, s3.num);
                            break;
                        case "saglik": 
                            mapSaglik.put(s3.ngram, s3.num);
                            break;
                        case "siyasi": 
                            mapSiyasi.put(s3.ngram, s3.num);
                            break;
                        case "spor": 
                            mapSpor.put(s3.ngram, s3.num);
                            break;             
                }
            }
        }    
        
              
 //          birlesmisMetin.append(str);
          // System.out.println(str);
          

        };
  //      System.out.println(finalString);
    }
  }  
 // String finalString = birlesmisMetin.toString();
 
 /* // bütün blok stringe n-gram uygula:
  Vector v2 = new Vector();
   Vector v3 = new Vector();
        int m=2;
        for (int j=0;j<finalString.length()-m+1;j++) {
            String y = finalString.substring(j,j+m);
            boolean buldu2 = false;
            for(int h=0;h<v2.size();h++) {
                sayac s2= (sayac)v2.elementAt(h);
                if (y.equals(s2.ngram)){
                    buldu2=true;
                    s2.num++;
                }
            }
            if (!buldu2) 
                v2.add(new sayac(1,y));
            }
        for(int z=0; z<v2.size();z++){
            sayac s3= (sayac)v2.elementAt(z);
            if (s3.num>=50) 
            {
                v3.add(new sayac(s3.num,s3.ngram));
                System.out.println(s3.ngram+";"+s3.num);
            }
            
        }
  
*/
 
 // kategorilendirilerek farklı dict.'lere atılan n-gramları listeleme (75%) :
int sayac1=0;
for (Map.Entry<String, Integer> entry : mapEkonomi.entrySet())
{
    System.out.println(entry.getKey() + " -- " + entry.getValue());
    sayac1++;
    System.out.println("sayaç : Ekonomi   "+sayac1);
}
sayac1=0;
for (Map.Entry<String, Integer> entry : mapMagazin.entrySet())
{
    System.out.println(entry.getKey() + " -- " + entry.getValue());
    sayac1++;
    System.out.println("sayaç : Magazin   "+sayac1);
}
sayac1=0;
for (Map.Entry<String, Integer> entry : mapSaglik.entrySet())
{
    System.out.println(entry.getKey() + " -- " + entry.getValue());
    sayac1++;
    System.out.println("sayaç : Saglik   "+sayac1);
}
sayac1=0;
for (Map.Entry<String, Integer> entry : mapSpor.entrySet())
{
    System.out.println(entry.getKey() + " -- " + entry.getValue());
    sayac1++;
    System.out.println("sayaç : Spor   "+sayac1);
}
sayac1=0;
for (Map.Entry<String, Integer> entry : mapSiyasi.entrySet())
{
    System.out.println(entry.getKey() + " -- " + entry.getValue());
    sayac1++;
    System.out.println("sayaç : Siyasi   "+sayac1);
}
}
}