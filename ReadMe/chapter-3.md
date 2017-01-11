#Diptiman Mitra
# learning-spark-chapter 3
this repo includes all my notes and poc on apache spark.

RDD Basics :

An RDD in Spark is simply an immutable distributed collection of objects. Each RDD
is split into multiple partitions, which may be computed on different nodes of the
cluster. RDDs can contain any type of Python, Java, or Scala objects, including userdefined
classes.

RDDs offer two types of operations: transformations and actions.
-Transformations construct a new RDD from a previous one.

-Actions, on the other hand, compute a result based on an RDD, and either return it to
the driver program or save it to an external storage system (e.g., HDFS).

Spark’s RDDs are by default recomputed each time you run an action on
them. If you would like to reuse an RDD in multiple actions, you can ask Spark to
persist it using RDD.persist().

Every Spark program and shell session will work as follows:
1. Create some input RDDs from external data.
2. Transform them to define new RDDs using transformations like filter().
3. Ask Spark to persist() any intermediate RDDs that will need to be reused.
4. Launch actions such as count() and first() to kick off a parallel computation,
which is then optimized and executed by Spark.

Creating RDDs :

Spark provides two ways to create RDDs: loading an external dataset and parallelizing
a collection in your driver program.

1. create your own RDDs in the shell and perform operations on them. Keep in
mind, however, that outside of prototyping and testing, this is not widely used since
it requires that you have your entire dataset in memory on one machine.

Example 3-6. parallelize() method in Scala
val lines = sc.parallelize(List("pandas", "i like pandas"))

Example 3-7. parallelize() method in Java
JavaRDD<String> lines = sc.parallelize(Arrays.asList("pandas", "i like pandas"));

2. Loading external datasets 

Example 3-9. textFile() method in Scala
val lines = sc.textFile("/path/to/README.md")

Example 3-10. textFile() method in Java
JavaRDD<String> lines = sc.textFile("/path/to/README.md");


