
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
  
    String denenecekHaberMetni = "banka";

    ArrayList<Double> ekonomiOlasilik = new ArrayList<>();
    ArrayList<Double> siyasiOlasilik = new ArrayList<>();
    ArrayList<Double> sporOlasilik = new ArrayList<>();
    ArrayList<Double> magazinOlasilik = new ArrayList<>();
    ArrayList<Double> saglikOlasilik = new ArrayList<>();  
 
    int rnd = 0, eslesme =0;
    File klasor1150haber = new File("1150haber");
    File[] list=klasor1150haber.listFiles(); 

    //  File kategori[] = new File("1150haber");
    // ArrayList <String> dosyaAdlari = new ArrayList <String> ();
    
    Map<String, String> testKumesiDosyaAdlariKategorileri = new HashMap<String, String>();
    Map<String, String> gercekDosyaKategorileri = new HashMap<String, String>();
    Map<String, String> tahminEdilenDosyaKategorileri = new HashMap<String, String>();
    
    Map<String, Integer> mapEkonomi = new HashMap<String,Integer>();
    Map<String, Integer> mapMagazin = new HashMap<String,Integer>();
    Map<String, Integer> mapSaglik = new HashMap<String,Integer>();
    Map<String, Integer> mapSiyasi = new HashMap<String,Integer>();
    Map<String, Integer> mapSpor = new HashMap<String,Integer>();
    
    Map<String, Integer> testEkonomi = new HashMap<String,Integer>();
    Map<String, Integer> testMagazin = new HashMap<String,Integer>();
    Map<String, Integer> testSaglik = new HashMap<String,Integer>();
    Map<String, Integer> testSiyasi = new HashMap<String,Integer>();
    Map<String, Integer> testSpor = new HashMap<String,Integer>();
//dosya ve klasörleri okuma: 

  for(int i=0;i<list.length;i++){
   // System.out.println(list[i].getName()+"---------------"); //klasör adlarını yazıyor
    if (list[i].isDirectory()) {
        for (File dosya:list[i].listFiles()){
            
            gercekDosyaKategorileri.put(dosya.getName(),list[i].getName());
            
         //   System.out.println(dosya.getName()); //alt klasörlerdeki dosyaların adlarını yazıyor
           //List<String> lines = Files.readAllLines(Paths.get(dosya.getName()), StandardCharsets.UTF_8);
            //String text = new String(Files.readAllBytes(Paths.get(dosya.getName())), StandardCharsets.UTF_8);
            // String icerik = new Scanner(dosya).useDelimiter("\\A").next();
            FileInputStream fis = new FileInputStream(dosya);
            byte[] data = new byte[(int) dosya.length()];
            fis.read(data);
            fis.close();
           String str2 = new String(data, "UTF-8");
           String str = str2.toLowerCase();
          //  System.out.println(str);
           
          
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
            if (!buldu2)   v2.add(new sayac(1,y));
        }
        rnd++;

        
        for(int z=0; z<v2.size();z++){
            sayac s3= (sayac)v2.elementAt(z);
            if (s3.num>=50 && rnd%4!=1)  // eğitim kümesi burada oluşturuluyor 
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
            if ( rnd%4==1)   // test kümesi burada oluşturuluyor 
            {
            
                    testKumesiDosyaAdlariKategorileri.put(dosya.getName(),list[i].getName());
                
                    switch (list[i].getName()){
                        case "ekonomi": 
                            testEkonomi.put(s3.ngram, s3.num);
                            break;
                        case "magazin": 
                            testMagazin.put(s3.ngram, s3.num);
                            break;
                        case "saglik": 
                            testSaglik.put(s3.ngram, s3.num);
                            break;
                        case "siyasi": 
                            testSiyasi.put(s3.ngram, s3.num);
                            break;
                        case "spor": 
                            testSpor.put(s3.ngram, s3.num);
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
        
        //denenecek haberi n-gramlara ayır 
 
        
            
            
            /*
           sayac1 = 0;
            for (Map.Entry<String, String> entry : gercekDosyaKategorileri.entrySet())
        {
            System.out.println(entry.getKey() + " -- " + entry.getValue());
            sayac1++;
            System.out.println("sayaç :    "+sayac1);
        }
            sayac1 = 0;
            for (Map.Entry<String, String> entry : testKumesiDosyaAdlariKategorileri.entrySet())
        {
            System.out.println(entry.getKey() + " -- " + entry.getValue());
            sayac1++;
            System.out.println("sayaç :    "+sayac1);
        }
            */

            int sayac5 = 0;
            
            for(int i=0;i<list.length;i++){ // test kümesine işlem yapma döngüsü
              //System.out.println(list[i].getName()+"---------------"); //klasör adlarını yazıyor
                 if (list[i].isDirectory()) {
                      for (File dosya:list[i].listFiles()){
                          if (testKumesiDosyaAdlariKategorileri.containsKey(dosya.getName())) {
                              

                              //test kümesi dosyasını okuma:
                              FileInputStream fis4 = new FileInputStream(dosya);
                              byte[] data4 = new byte[(int) dosya.length()];
                              fis4.read(data4);
                              fis4.close();
                              String str4 = new String(data4, "UTF-8");
                              String str3 = str4.toLowerCase();
                              
                              Vector v3 = new Vector();
        int m=2;
        for (int j=0;j<str3.length()-m+1;j++) {
            String y = str3.substring(j,j+m);
            boolean buldu2 = false;
            for(int h=0;h<v3.size();h++) {
                sayac s2= (sayac)v3.elementAt(h);
                if (y.equals(s2.ngram)){
                    buldu2=true;
                    s2.num++;
                }
            }
            if (!buldu2) 
                v3.add(new sayac(1,y));
            }
        int sayac1=0;
        int pEkonomi=0,pMagazin=0,pSiyasi=0,pSpor=0,pSaglik=0,olasilik=0;
        double toplam = 0;
        
        ekonomiOlasilik.clear();
        siyasiOlasilik.clear();
        sporOlasilik.clear();
        saglikOlasilik.clear();
        magazinOlasilik.clear();
        
        
        for(int z=0; z<v3.size();z++){
            sayac s2= (sayac)v3.elementAt(z);
            System.out.println("Aranacak metindeki ngram -------- : "+s2.ngram+";"+s2.num);
            //int ekonomideBulunan = 0;
                for (Map.Entry<String, Integer> entry : mapEkonomi.entrySet())
                {/*
                 System.out.println(entry.getKey() + " -- " + entry.getValue());
                 sayac1++;
                 System.out.println("sayaç : Ekonomi   "+sayac1);
                */
                  //  System.out.println("------"+s2.ngram+" "+entry.getKey());
                    if (s2.ngram.equals(entry.getKey())){
                     //   System.out.println("Ekonomi kategorisinde bulunan "+s2.ngram+" "+entry.getValue()+" adettir");
                     pEkonomi = entry.getValue();
                    }
                }
                for (Map.Entry<String, Integer> entry : mapMagazin.entrySet())
                {/*
                 System.out.println(entry.getKey() + " -- " + entry.getValue());
                 sayac1++;
                 System.out.println("sayaç : Ekonomi   "+sayac1);
                */
                //    System.out.println("------"+s2.ngram+" "+entry.getKey());
                    if (s2.ngram.equals(entry.getKey())){
                     //   System.out.println("Ekonomi kategorisinde bulunan "+s2.ngram+" "+entry.getValue()+" adettir");
                     pMagazin = entry.getValue();
                    }
                }
                for (Map.Entry<String, Integer> entry : mapSaglik.entrySet())
                {/*
                 System.out.println(entry.getKey() + " -- " + entry.getValue());
                 sayac1++;
                 System.out.println("sayaç : Ekonomi   "+sayac1);
                */
                 //   System.out.println("------"+s2.ngram+" "+entry.getKey());
                    if (s2.ngram.equals(entry.getKey())){
                     //   System.out.println("Ekonomi kategorisinde bulunan "+s2.ngram+" "+entry.getValue()+" adettir");
                     pSaglik = entry.getValue();
                    }
                }
                for (Map.Entry<String, Integer> entry : mapSiyasi.entrySet())
                {/*
                 System.out.println(entry.getKey() + " -- " + entry.getValue());
                 sayac1++;
                 System.out.println("sayaç : Ekonomi   "+sayac1);
                */
                 //   System.out.println("------"+s2.ngram+" "+entry.getKey());
                    if (s2.ngram.equals(entry.getKey())){
                     //   System.out.println("Ekonomi kategorisinde bulunan "+s2.ngram+" "+entry.getValue()+" adettir");
                     pSiyasi = entry.getValue();
                    }
                }
                for (Map.Entry<String, Integer> entry : mapSpor.entrySet())
                {/*
                 System.out.println(entry.getKey() + " -- " + entry.getValue());
                 sayac1++;
                 System.out.println("sayaç : Ekonomi   "+sayac1);
                */
                   // System.out.println("------"+s2.ngram+" "+entry.getKey());
                    if (s2.ngram.equals(entry.getKey())){
                     //   System.out.println("Ekonomi kategorisinde bulunan "+s2.ngram+" "+entry.getValue()+" adettir");
                     pSpor = entry.getValue();
                    }
                }
            
            toplam = pMagazin+pEkonomi+pSiyasi+pSpor+pSaglik;
            ekonomiOlasilik.add(pEkonomi/toplam);
            siyasiOlasilik.add(pSiyasi/toplam);
            sporOlasilik.add(pSpor/toplam);
            magazinOlasilik.add(pMagazin/toplam);
            saglikOlasilik.add(pSaglik/toplam);
            
            
            System.out.println("ekonomi olasılık : "+ pEkonomi/toplam);
            System.out.println("spor olasılık : "+ pSpor/toplam);
            System.out.println("magazin olasılık : "+ pMagazin/toplam);
            System.out.println("saglik olasılık : "+ pSaglik/toplam);
            System.out.println("siyasi olasılık : "+ pSiyasi/toplam);
            
            
            }
            
        //deneme 
      //  for (double d : ekonomiOlasilik) System.out.println("*************  "+d);
        
       
        
        
        
            Map<String, Double> olasiliklar = new HashMap<String,Double>();
            double carpim = 1.0;
            for (double deger: ekonomiOlasilik){
                
            if (deger>0) carpim*=deger;
            System.out.println("------------ "+deger+" "+ carpim );
            }
            olasiliklar.put("ekonomi", carpim);
            
            carpim = 1.0;
            for (double deger: magazinOlasilik){
               if (deger>0) carpim*=deger;
            }
            olasiliklar.put("magazin", carpim);
            
            carpim = 1.0;
            for (double deger: siyasiOlasilik){
               if (deger>0) carpim*=deger;
            }
            olasiliklar.put("siyasi", carpim);
            
            carpim = 1.0;
            for (double deger: sporOlasilik){
               if (deger>0) carpim*=deger;
            }
            olasiliklar.put("spor", carpim);
            
            carpim = 1.0;
            for (double deger: saglikOlasilik){
               if (deger>0) carpim*=deger;
            }
            olasiliklar.put("saglik", carpim);
        double max=0;
        String maxKategorisi="";
        double olasilikToplam = 0;
        for (Map.Entry<String, Double> kategori : olasiliklar.entrySet())
        {
            olasilikToplam += kategori.getValue();
        }
        olasilikToplam *= 0.2; //naive bayes genel olasılık çarpımı yani 1/5
        for (Map.Entry<String, Double> kategori : olasiliklar.entrySet())
        {  
                System.out.println("kategori value : "+kategori.getValue());
                System.out.println("kategori adi : "+kategori.getKey());
                System.out.println("kategori olasılık : "+kategori.getValue()/olasilikToplam);
          //      System.out.println("maximum : "+max);
            if (kategori.getValue()>max){
                max = kategori.getValue();
                maxKategorisi = kategori.getKey();
          
            }
        }
            
            System.out.println("En büyük ihtimal : "+maxKategorisi);
            System.out.println("gerçek klasör : "+list[i].getName());
          
            if (maxKategorisi.equals(list[i].getName())) {
                eslesme++;
            }
          //  testKumesiDosyaAdlariKategorileri
          //  if (maxKategorisi == )                  
                              
                          }  //contains true dönüş parantezi 
                            
                      }
                 }
            }
            
            
        /*     for (Map.Entry<String, String> entry : testKumesiDosyaAdlariKategorileri.entrySet())
        {
            System.out.println(entry.getKey()+"___________"+entry.getValue());
            
            File file3 = new File("1150haber/"+entry.getKey()+"/"+entry.getValue());
            FileInputStream fis3 = new FileInputStream(file3);
            byte[] data3 = new byte[(int) file3.length()];
            fis3.read(data3);
            fis3.close();
            String denenecekHaberMetni3 = new String(data3, "UTF-8");
            String denenecekHaberMetni4 = denenecekHaberMetni3.toLowerCase();
            System.out.println(denenecekHaberMetni4);
        }*/
            
            
        //hesaplamalar 
        
        double tumTestKumesi = testKumesiDosyaAdlariKategorileri.size();
        double TPdegeri = eslesme;
        double FNdegeri = tumTestKumesi-TPdegeri;
        double recall = TPdegeri/FNdegeri;
        double precision = TPdegeri / tumTestKumesi;                 //TPdegeri/(0.2);
        double fMeasure = 2 * recall * precision / (recall+precision);
        
        
        
        System.out.println("--------------------------------------------------");
        
        System.out.println("Recall Değeri : "+recall);
        System.out.println("Precision Değeri : "+precision);
        System.out.println("F-Measure Değeri : "+fMeasure);
        

        }//main parantezi
}


   
