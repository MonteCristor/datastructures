package com.demo.datastructures.sparsearray

import java.io.PrintWriter

import scala.collection.mutable.ArrayBuffer
import scala.io.{BufferedSource, Source}

/**
  * @Author: sw
  * @sidereal_csday: 2019-05-05
  */
object SparseArrayDemo {

  def main(args: Array[String]): Unit = {

    //1. 先把原始的地图创建(二维数组)
    val rows  = 11
    val cols = 11
    val chessMap1: Array[Array[Int]] = Array.ofDim[Int](rows, cols)
    chessMap1(1)(2) = 1 //表示黑子
    chessMap1(2)(3) = 2 //表示蓝子

    //输出原始的地图
    println("原始的地图入下..")
    for (item1 <- chessMap1) {
      for (item2 <- item1) {
        printf("%d\t", item2)

      }
      println()
    }
    //使用稀疏数组来对chessMap1进行压缩
    //思路
    //1. 使用ArrayBuffer 来保存有效数据
    //2. 每一个数据使用Node 对象来表示
    //3. Node 的结构 class Node(val row:Int, val col:Int,val value:Int)
    val sparseArray = ArrayBuffer[Node]()
    val node1 = new Node(rows, cols, 0)
    sparseArray.append(node1)
    //遍历chessMap1, 每发现一个非0的值，就创建给Node ,并加入到sparseArray
    for (i <- 0 until chessMap1.length) {//列
      for (j <- 0 until chessMap1(i).length) {
        //列
        if(chessMap1(i)(j) != 0) {
          //不为零的数据为需要保存的数据
          //Node 对象
          val node2 = new Node(i, j, chessMap1(i)(j))
          sparseArray.append(node2)
        }
      }
    }

    //输出稀疏数组
    println("稀疏数组的情况")
    for (node <- sparseArray) {
      printf("%d\t%d\t%d\t\n", node.row, node.col, node.value)
    }

//    写入本地文本文件
    val out = new PrintWriter("C:\\Users\\Administrator\\Desktop\\chess.txt")
    for (elem <- sparseArray) {
      out.println(elem.row+"\t"+elem.col+"\t"+elem.value)
    }
    out.flush()
    out.close()

    //读取本地文本文件获取内容
    val source: BufferedSource = Source.fromFile("C:\\Users\\Administrator\\Desktop\\chess.txt")
//    for (elem <- source.getLines()) {
//      println("------------"+elem)
//    }
    val zipedMessage = new ArrayBuffer[Node]
    val strings: Iterator[String] = source.getLines()
    for (elem <- strings) {
      val rows: Array[String] = elem.split("\t")
      zipedMessage.append(new Node(rows(0).toInt,rows(1).toInt,rows(2).toInt))
    }

    //将稀疏数组，恢复成原始的地图
    //1. 先从sparseArray 读取第一个node ,并创建新的地图
//    for (elem <- zipedMessage) {
//      printf("zipedMessage %d\t%d\t%d\t\n", elem.row, elem.col, elem.value)
//    }
    val fistNode: Node = zipedMessage(0)
    val chessMap2: Array[Array[Int]] = Array.ofDim[Int](fistNode.row, (fistNode.col))
    //2. 从sparseArray第二个数据开始遍历，并将数组恢复到chessMap2
    for (i <- 1 until zipedMessage.length) {
      val lineMessage = zipedMessage(i)
      chessMap2(lineMessage.row)(lineMessage.col) = lineMessage.value
    }
    //再次输出恢复后的原始地图
    println("恢复后的图为：")
    for (elem1 <- chessMap2) {
      for (elem2 <- elem1) {
        printf("%d\t", elem2)
      }
      println()
    }

  }

}
//每一个数据使用Node 对象来表示
class Node(val row: Int, val col: Int, val value: Int)