/* Author: Robert Attaway
 * 
 * Class: CSC3410 T/Th 11:00 a.m. - 12:15 p.m.
 * 
 * Assignment: 7
 * 
 * Due Date: December 3rd, 2013
 * 
 * DESCRIPTION: Extened thread class that splits an array into 1/5 in order to
 *              allow the calling program to have 5 threads to work equally on the
 *              same number of elements in order to compare the results of many
 *              threads to a single threads execution time.
 * 
 * USAGE: Thread.Start() after creating a new instance
 */
package arrayscaling;
public class ScalingThread extends Thread {
  private int startPosition;
  private int scaleSize;
  private int[] sharedData;
  
  // constructor
  // pre-condition: Array of integers, a start position, the number of elements to
  //                process and the name for the thread
  public ScalingThread(int[] shared, int start, int size, String ThreadName)
  {
    super(ThreadName);
    startPosition = start;
    scaleSize = size;
    sharedData = shared;
  }
  
  // pre-condition: array of unscaled values
  // post-condition: array of scaled value
  // invariant: i < startingPosition + scale size
  @Override public void run()
  {
    for(int i = startPosition; i< startPosition + scaleSize; i++)
    {
      sharedData[i]*=3;
    }    
  }
  
}
