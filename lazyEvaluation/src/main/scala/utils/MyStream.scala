package utils

import common._

/**
 *  My Stream
 *
 */
object MyStream {

  abstract class AbstractLazyList
  case class EmptyLazyList() extends AbstractLazyList {
     
    override def toString():String =   " ... "
        
  }
  case class LazyList(val head: Int , val tail:  AbstractLazyList) extends AbstractLazyList {
    //println(head+ "...") 
    override def toString():String =  head.toString() + " ... "
  }
  
  def isEmpty(l: => AbstractLazyList) : Boolean = l match 
  {
    case EmptyLazyList() => true
    case LazyList(head,tail) => false
  }
  
  
  def size(l: AbstractLazyList) : Int = l match 
  {
    case EmptyLazyList() => 0
    case LazyList(head,tail) => 1+size(tail)
  }
  
  def equalsList(l: AbstractLazyList, other: AbstractLazyList) : Boolean = l match 
  {
    case EmptyLazyList() => other  match {
      case EmptyLazyList() => true
      case LazyList(head,tail) => false 
    }
    case LazyList(head1,tail1) => other match {
      case EmptyLazyList() => true
      case LazyList(head2,tail2) => 
        {
         (head1==head2) && equalsList(tail1, tail2)
      } 
    }
  }
  
  /*
   * Agrega un elemento primero 
   */
  def add(element: Int, l: => AbstractLazyList) : AbstractLazyList = l  match 
  {
    case EmptyLazyList() => new LazyList(element,l)
    case LazyList(head,tail) => new LazyList(element,add(head,tail))
  }
    
  /*
   * Agrega un elemento en la ultima posición 
   */
  def addLast(element: Int, l: => AbstractLazyList) : AbstractLazyList = l  match 
  {
    case EmptyLazyList() => new LazyList(element,new EmptyLazyList())
    case LazyList(head,tail) => new LazyList(head,add(element,tail))
  }
  
  /** crea una lista desde un rango */
  def range(start: Int, end: Int, step: Int): AbstractLazyList = 
  {
    if(start>end) new EmptyLazyList()
    else add(start,range(start+step,end,step)) 
    
    
  }
  
  /** imprime todos los valores */
  def toStringAll(l: => AbstractLazyList) : String = l  match {
    case EmptyLazyList() => ".."
    case LazyList(head,tail) => head.toString()+", "+toStringAll(tail)
  }
 
  
}
