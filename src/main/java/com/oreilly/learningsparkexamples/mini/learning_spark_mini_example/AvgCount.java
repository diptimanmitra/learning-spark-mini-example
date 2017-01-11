package com.oreilly.learningsparkexamples.mini.learning_spark_mini_example;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;

public class AvgCount implements Serializable{

	public int total;
	public int num;
	public AvgCount(int total,int num){
		this.total = total;
		this.num = num;
	}
	public double avg(){
		return total/(double) num;
	}
	
	
	public static void main(String[] arg){
	
		Function2<AvgCount, Integer, AvgCount> addAndCount =
				new Function2<AvgCount, Integer, AvgCount>() {
					public AvgCount call(AvgCount a, Integer x){
						a.total +=x;
						a.num += 1;
						return a;
					}
				};

				Function2<AvgCount, AvgCount, AvgCount> combine =
						new Function2<AvgCount, AvgCount, AvgCount>() {
							public AvgCount call(AvgCount a, AvgCount b){
								a.total += b.total;
								a.num += b.num;
								return a;
							}
						};	
	       System.out.println( "Hello World!" );
	        //Create Java Spark Context
	        SparkConf conf = new SparkConf().setAppName("wordCount").setMaster("local[2]");;//.setMaster("spark://192.168.1.70:62816").set("spark.ui.port‌​","62816");
	        JavaSparkContext sc = new JavaSparkContext(conf);

		JavaRDD<Integer> l2 = sc.parallelize(Arrays.asList(1,2,3));
		AvgCount initial = new AvgCount(0, 0);
		AvgCount result = l2.aggregate(initial, addAndCount, combine);
		System.out.println("result::"+result.avg());
	}
}
