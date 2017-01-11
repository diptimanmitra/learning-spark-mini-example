#Diptiman Mitra
# learning-spark-chapter 2
this repo includes all my notes and poc on apache spark.


1. Standalone Applications :

Spark can be linked into standalone applications
in either Java, Scala, or Python. The main difference from using it in the shell
is that you need to initialize your own SparkContext. After that, the API is the same.
In Java and Scala, you give your
application a Maven dependency on the spark-core artifact

2. Initializing a SparkContext : 

Example 2-8. Initializing Spark in Scala
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
val conf = new SparkConf().setMaster("local").setAppName("My App")
val sc = new SparkContext(conf)

Example 2-9. Initializing Spark in Java
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
SparkConf conf = new SparkConf().setMaster("local").setAppName("My App");
JavaSparkContext sc = new JavaSparkContext(conf);

These examples show the minimal way to initialize a SparkContext, where you pass
two parameters:

- A cluster URL, namely local in these examples, which tells Spark how to connect
to a cluster. local is a special value that runs Spark on one thread on the local
machine, without connecting to a cluster.

-An application name, namely My App in these examples. This will identify your
application on the cluster manager's UI if you connect to a cluster.



