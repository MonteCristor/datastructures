package com.demo.datastructures.sparsearray

/**
  * @Author: sw
  * @sidereal_csday: 2019-05-05
  */
object SparseArrayDemo {

  def main(args: Array[String]): Unit = {

    //1. 先把原始的地图创建(二维数组)

    //输出原始的地图

    //使用稀疏数组来对chessMap1进行压缩
    //思路
    //1. 使用ArrayBuffer 来保存有效数据
    //2. 每一个数据使用Node 对象来表示
    //3. Node 的结构 class Node(val row:Int, val col:Int,val value:Int)


  }

}
//每一个数据使用Node 对象来表示
class Node(val row: Int, val col: Int, val value: Int)