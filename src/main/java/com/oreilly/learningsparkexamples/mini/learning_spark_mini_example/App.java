package com.oreilly.learningsparkexamples.mini.learning_spark_mini_example;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import com.sun.org.apache.regexp.internal.recompile;

import scala.Tuple2;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //Create Java Spark Context
        SparkConf conf = new SparkConf().setAppName("wordCount").setMaster("local[2]");;//.setMaster("spark://192.168.1.70:62816").set("spark.ui.port‌​","62816");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        JavaRDD<String> lines = sc.parallelize(Arrays.asList("pandas","i like pandas"));
        JavaRDD<String> word = sc.parallelize(Arrays.asList("pandas","i like pandas"));
        JavaRDD<String> lineWords = lines.union(word);
        for(String line : lineWords.take(10)){
        	System.out.println(line);
        }
        //Load our input data
        JavaRDD<String> input = sc.textFile("C:\\Users\\monal_000\\Downloads\\spark-2.0.1-bin-hadoop2.7\\spark-2.0.1-bin-hadoop2.7\\README.md");
        //Split up into words
        JavaRDD<String> words = input.flatMap(new FlatMapFunction<String, String>() {
        	public Iterable<String> call(String x){
        		return Arrays.asList(x.split(" "));
        	}
		});
        System.out.println(words.first());
//        JavaRDD<String> words = input.flatMap(str -> Arrays.asList(s.split(" ")));
        //Transform into pair and count
        JavaPairRDD<String, Integer> counts = words.mapToPair(new PairFunction<String, String, Integer>() {
		public Tuple2<String, Integer> call(String x){
			return new Tuple2<String, Integer>(x, 1);
		}
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
				public Integer call(Integer x, Integer y){
					return x+y;
				}
		});
        
        //Examples Practice 
      //Example 1
        JavaRDD<String> l1 = sc.parallelize(Arrays.asList("pandas","i like pandas"));
        JavaRDD<String> w1 = l1.flatMap(new FlatMapFunction<String, String>() {
        	public Iterable<String> call(String x){
        		return Arrays.asList(x.split(" "));
        	}
		});
        //Example 2
        JavaRDD<Integer> l2 = sc.parallelize(Arrays.asList(1,2,3));//.asList("1","2","3"));
        Integer w2 = l2.reduce(new Function2<Integer, Integer, Integer>() {
        	public Integer call(Integer x,Integer y){
        		return x+y;
        	}
		});
        System.out.println("w2 :"+ w2);
        
        
        
        counts.saveAsTextFile("C:\\Users\\monal_000\\dummy\\README1.txt");
    	
		
    }
    	
}
