/* Author: Robert Attaway
 * 
 * Class: CSC3410 T/Th 11:00 a.m. - 12:15 p.m.
 * 
 * Assignment: 7
 * 
 * Due Date: December 3rd, 2013
 * 
 * DESCRIPTION: A comparison of multi threading to single threading performance.
 *              The test starts out with an array of integers. A loop then scales
 *              the integers by a factor of three.  Then split the array up into
 *              five different units such that five threads can execute at the
 *              same time.  Then compare the time it takes a single thread to 
 *              scale the array versus the time it takes 5 threads to scale 1/5
 *              of the array. The user only needs to run the program.
 *            
 *              It was noted that on the target machine it took an array of
 *              50,000,000 elements before any noticable clock time was recordable
 *              
 * 
 * USAGE: java -jar arrayScaling.jar
 * 
 * CLASSES: ArrayScaling
 *          ScalingThread
 * 
 * ALGORITHMS: Post order tree traversal to display postfix and solve the expression
 *             when a variable is supplied in the expression
 * 
 * ORIGINAL DESCRIPTION: You are to write a program name arrayScaling.java that 
 * will prompt the user to enter a value for n which will be the amount of randaom 
 * numbers your program will generate. These randomly generated numbers will range 
 * from 1 - 25 and be placed them in the array.
 * 
 * The program will scale this array by 3 (i.e. multiply each element of the array 
 * by 3) using simple multiplication and time the amount of time it takes to do 
 * this.
 * 
 * The program will now scale the original array by 3 but by using Multi Treading 
 * and time this process as well -- You must use 5 threads.
 * 
 * Finally, the program would compare the time it took for these two procedures 
 * and and print out a message indicating which is the better method and by how 
 * much time. 
 * 
 * Just a simple comment 
 *
 */
package arrayscaling;

import java.util.*;
public class ArrayScaling {

  /**
   * @param args the command line arguments
   */

  
  public static void main(String[] args) throws InterruptedException {
    // TODO code application logic here
    // Change the size variable such that it will be user promptable
    int size = 50000000;
    int partitions = 5;
    int partitionSize = size / partitions;
    Random r = new Random();
    r.setSeed(System.currentTimeMillis());
    int[] scaledValues = new int[size];
    for(int i = 0; i< size; i++)
    {
      scaledValues[i] = r.nextInt(25) + 1;
    }
    
    Date s = new Date();
    long nst = System.nanoTime();
    for(int i = 0; i< size; i++)
    {
      scaledValues[i]*=3;
    }
    long est = System.nanoTime();
    Date x = new Date();
    long millisecondsSingleThread = est - nst;//( x.getTime() - s.getTime());
    System.out.println("Time is " + millisecondsSingleThread + " milliseconds for " + size + " elements");
    
    ScalingThread thread1 = new ScalingThread(scaledValues,        0, partitionSize, "Thread 1");
    ScalingThread thread2 = new ScalingThread(scaledValues, 1 * partitionSize, partitionSize, "Thread 2");
    ScalingThread thread3 = new ScalingThread(scaledValues, 2 * partitionSize, partitionSize, "Thread 3");
    ScalingThread thread4 = new ScalingThread(scaledValues, 3 * partitionSize, partitionSize, "Thread 4");
    ScalingThread thread5 = new ScalingThread(scaledValues, 4 * partitionSize, partitionSize, "Thread 5");
    
    Date y = new Date();
    nst = System.nanoTime();
    thread1.start();
    thread1.join();
    thread2.start();
    thread2.join();
    thread3.start();
    thread3.join();
    thread4.start();
    thread4.join();
    thread5.start();
    thread5.join();
    Date z = new Date();
    est = System.nanoTime();
    
    long millisecondsMultiThread = ( est - nst);
    System.out.println("Time is " + millisecondsMultiThread + " milliseconds for " + size + " elements using 5 threads");
    
    if(millisecondsMultiThread < millisecondsSingleThread)
    {
      System.out.println("For the current run the multithreaded program took " + (millisecondsSingleThread - millisecondsMultiThread) + " less milliseconds than a single thread");
    }
    else
    {
      System.out.println("For the current run the singled threaded program took " + (millisecondsMultiThread - millisecondsSingleThread ) + " less milliseconds than a a multi thread");
    }
    
}
}

