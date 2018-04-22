package com.example.spark

import ml.dmlc.xgboost4j.scala.spark.XGBoost
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.sql.{Row, SparkSession}

object XgboostDemo {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local").getOrCreate()
    val data = spark.read.format("libsvm").load("/data/soft/new/spark/data/mllib/sample_libsvm_data.txt")
    val Array(training, test) = data.randomSplit(Array(0.7, 0.3))
    val params = Map(
      "eta" -> 0.3,
      "max_depth" -> 6,
      "silent" -> 0,
      "objective" -> "reg:linear",
      "lambda" -> 1,
      "nthread" -> 1
    )
    val model = XGBoost.trainWithDataFrame(training, params, 5, 1, null, null, false, Float.NaN, "features", "label")
    val predict = model.transform(test)
    val scoreAndLabels = predict.select(model.getPredictionCol, model.getLabelCol).rdd.map { case Row(score: Double, label: Double) => (score, label) }
    val metric = new BinaryClassificationMetrics(scoreAndLabels)
    val auc = metric.areaUnderROC()
    println("auc:" + auc)
  }
}
