package com.demo.datastructures.search
import util.control.Breaks._
/**
  * @Author: sw
  * @sidereal_csday: 2019-05-06
  */
object BinarySearch {
  def main(args: Array[String]): Unit = {

    val arr = Array(1, 8, 10, 89,1000, 1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000)
    binarySearch(arr,0,arr.length -1, 1000)

    //要求：
    /*
    课后思考题1： {1,8, 10, 89, 1000, 1000，1234}
    当一个有序数组中，有多个相同的数值时，如何将所有的数值对应下标都查找到，比如这里的 1000.

     */
  }

  //使用递归的方式，编写二分查找
  /**
    *
    * @param arr        待查找的数组
    * @param leftIndex  数组左边的索引
    * @param rightIndex 数组右边的索引
    * @param findVal    你要查找的数
    */
  def binarySearch(arr: Array[Int], leftIndex: Int, rightIndex: Int, findVal: Int): Unit = {

    //判断什么时候是找不到
    if(rightIndex < leftIndex) {
      println("找不到")
      return
    }


    //得到中间的mid
    val midIndex = (leftIndex + rightIndex) / 2
    val midVal = arr(midIndex)

    //比较
    if(midVal > findVal) { //向左递归查找
      binarySearch(arr,leftIndex, midIndex - 1,findVal)
    } else if (midVal < findVal) { //向右递归查找
      binarySearch(arr,midIndex+1,rightIndex, findVal)
    } else {
      breakable{
      for (i <- midIndex to rightIndex){
          if(arr(midIndex) != arr(i)){
            break()
          }
          println("找到了 索引为=" + i)
        }
      }
      breakable {
        for (i <- leftIndex to (midIndex - 1)) {
          if (arr(midIndex) != arr(i)) {
            break()
          }
          println("找到了 索引为=" + i)
        }
      }

    }

  }
}
