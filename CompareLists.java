import tester.*;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;
import tester.Tester;

class Point {
  int x, y;
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  double distance(Point other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }
}
class PointCompare implements Comparator<Point>{

  @Override
  public int compare(Point o1, Point o2) {
      if(o1.y<o2.y)
      {
        return -1;
      }
      else if(o1.y>o2.y)
      {
        return 1;
      }
      else{
        if(o1.x<o2.x)
        {
          return -1;
        }
        else if(o1.x>o2.x)
        {
          return 1;
        }
        else{
          return 0;
        }
      }
  }
}
class PointDistanceCompare implements Comparator<Point>{

  @Override
  public int compare(Point o1, Point o2) {
      Point ori = new Point(0, 0);
      if(o1.distance(ori)<o2.distance(ori)){
          return -1;
      }
      else if(o1.distance(ori)>o2.distance(ori)){
        return 1;
      }
      else{
        return 0;
      }
  }
}
class StringCompare implements Comparator<String>{

  @Override
  public int compare(String o1, String o2) {
      if(o1.compareTo(o2)<0)
      {
        return -1;
      }
      else if(o1.compareTo(o2)>0)
      {
        return 1;
      }
      else{
        return 0;
      }
  }
}
class StringLengthCompare implements Comparator<String>{

  @Override
  public int compare(String o1, String o2) {
      if(o1.length()<o2.length())
      {
        return -1;
      }
      else if(o1.length()>o2.length())
      {
        return 1;
      }
      else{
        return 0;
      }
  }
}
class BooleanCompare implements Comparator<Boolean>{

  @Override
  public int compare(Boolean o1, Boolean o2) {
      if(o1==true&&o2==false)
      {
        return 1;
      }
      else if(o1==false && o2==true){
        return -1;
      }
      else{
        return 0;
      }
  }
}
class CompareLists<E>{
  PointCompare pc = new PointCompare();
  PointDistanceCompare pdc = new PointDistanceCompare();
  StringCompare sc = new StringCompare();
  StringLengthCompare slc = new StringLengthCompare();
  BooleanCompare bc = new BooleanCompare();
  Point p1 = new Point(0, 0);
  Point p2 = new Point(0,0);
  Point p3 = new Point(0, 1);
  Point p4 = new Point(0, 2);
  Point p5 = new Point (1,2);
  String s1 = "abc";
  String s2 = "bcd";
  String s3 = "acb";
  String s4 = "bcd";
  String s5 = "acd";
  String s6 = "";
  String s7 = "a";
  String s8 = "ab";
  String s9 ="abcd";

  
  
   
  boolean testpointCompare(Tester t)
  {
      return t.checkExpect(pc.compare(p1, p2),0)
      &&t.checkExpect(pc.compare(p3, p4),-1)
      &&t.checkExpect(pc.compare(p4, p5), -1)
      &&t.checkExpect(pc.compare(p5, p4), 1)
      &&t.checkExpect(pc.compare(p5, p3), 1);
  }
  boolean testpointDistanceCompare(Tester t)
  {
     return t.checkExpect(pdc.compare(p1, p2), 0)
     &&t.checkExpect(pdc.compare(p3, p4),-1)
     &&t.checkExpect(pdc.compare(p5, p4),1)
     &&t.checkExpect(pdc.compare(p1, p3),-1);
  }
  boolean testStringCompare(Tester t)
  {
      return t.checkExpect(sc.compare(s1, s2),-1)
      && t.checkExpect(sc.compare(s3, s1),1)
      &&t.checkExpect(sc.compare(s3, s5), -1)
      &&t.checkExpect(sc.compare(s2, s4),0);
  }
  boolean testStringLengthCompare(Tester t)
  {
    return t.checkExpect(slc.compare(s6, s7), -1)
    &&t.checkExpect(slc.compare(s7, s6), 1)
    &&t.checkExpect(slc.compare(s4, s5),0)
    &&t.checkExpect(slc.compare(s8, s9),-1);
  }
  boolean testBooleanCompare(Tester t)
  {
    return t.checkExpect(bc.compare(true, false), 1)
    &&t.checkExpect(bc.compare(false, true),-1)
    &&t.checkExpect(bc.compare(true, true),0)
    &&t.checkExpect(bc.compare(false, false),0);
  }
  
  public static<E> E minimum(List<E> list,Comparator<E> cp){
      if(list.size()==0)
      {
        return null;
      }
      E min = list.get(0);
      for(int i=0;i<list.size();i++)
      {
        if(cp.compare(list.get(i), min)<0)
        {
          min = list.get(i);
        }
      }
      return (E)min;
  }
  public static <E>E minimum(E[] arr, Comparator<E>cp)
  {
      if(arr.length==0)
      {
        return null;
      }
      E min = arr[0];
      for(int i=0;i<arr.length;i++)
      {
        if(cp.compare(arr[i], min)<0)
        {
          min = arr[i];
        }
      }
      return min;
  }
  public static <E>List<E> greaterThan(List<E> list,Comparator<E> cp,E ele){
    if(list.size()==0)
    {
      return null;
    }
    List<E> l1 = new ArrayList<>();
    for(int i=0;i<list.size();i++)
    {
      if(cp.compare(list.get(i), ele)>0){
        l1.add(list.get(i));
      }
    }
    return l1;
  }
  public static <E>boolean inOrder(List<E> list,Comparator<E>cp)
  {
      boolean inO = true;
      for(int i=0;i<list.size();i++)
      {
          if(i!=list.size()-1)
          {
            int j=i+1;
            if(list.get(i)==null||list.get(j)==null)
            {
              throw new IllegalArgumentException("null value in list");
            }
            if(cp.compare(list.get(j), list.get(i))<0)
            {
               inO=false;
            }
          }
          else{
            if(list.get(i)==null)
            {
              throw new IllegalArgumentException("null value in list");
            }
          }
      }
      return inO;
  }

  public static <E>boolean inOrder(E[]arr,Comparator<E>cp){
    boolean inO = true;
    for(int i=0;i<arr.length;i++)
    {
      if(i!=arr.length-1)
      {
        int j=i+1;
        if(arr[i]==null||arr[j]==null)
        {
          throw new IllegalArgumentException("null value in array");
        }
        if(cp.compare(arr[j], arr[i])<0)
        {
          inO=false;
        }
      }
      else{
        if(arr[i]==null)
        {
          throw new IllegalArgumentException("null value in array");
        }
      }
    }
    return inO;
  }
  public static<E>List<E> merge(Comparator<E> cp, List<E> l1,List<E>l2)
  {
      for(int i=0;i<l1.size();i++)
      {
        if(l1.get(i)==null)
        {
          throw new IllegalArgumentException("null value in first list");
        }
      }
      for(int i=0;i<l2.size();i++)
      {
        if(l2.get(i)==null)
        {
          throw new IllegalArgumentException("null value in second list");
        }
      }
      List<E> newl = new ArrayList<>(){
        
      };
      int i=0,j=0;
      while(i<l1.size()&&j<l2.size())
      {
        if(cp.compare(l1.get(i),l2.get(j))<=0)
        {
          newl.add(l1.get(i));
          i++;
        }
        else{
          newl.add(l2.get(j));
          j++;
        }
      }
      while(i<l1.size())
      {
        newl.add(l1.get(i));
        i++;
      }
      while(j<l2.size())
      {
        newl.add(l2.get(j));
        j++;
      }
      return newl;
  }

  
  
  boolean testminimumEleList(Tester t){
    ArrayList<E>list = new ArrayList<>();
    list.add((E)s1);
    list.add((E)s2);
    list.add((E)s3);
    ArrayList<E>list1 = new ArrayList<>();
    ArrayList<E>list2 = new ArrayList<>();
    list2.add((E)s1);
    list2.add((E)s2);
    list2.add((E)s3);
    list2.add((E)s8);
    ArrayList<Boolean>list3 = new ArrayList<>();
    list3.add(true);
    list3.add(false);
    return t.checkExpect(minimum(list,(Comparator<E>) sc),s1)
    && t.checkExpect(minimum(list1, (Comparator<E>) sc), null)
    && t.checkExpect(minimum(list2, (Comparator<E>)slc), s8)
    && t.checkExpect(minimum((List<E>) list3, (Comparator<E>)bc), list3.get(1));
  }
  boolean testMinArr(Tester t)
  {
     String[] str = {s1,s2,s3};
     Point[] pot = {p1,p3,p4};
     return t.checkExpect(minimum((E[])str,(Comparator<E>)sc), s1)&&
     t.checkExpect(minimum((E[])pot,(Comparator<E>)pc), p1)
     &&t.checkExpect(minimum((E[])pot,(Comparator<E>)pdc), p1);
  }
  boolean testgreaterThan(Tester t)
  {
    ArrayList<E>list = new ArrayList<>();
    list.add((E)s1);
    list.add((E)s2);
    list.add((E)s3);
    ArrayList<E>list1 = new ArrayList<>();
    ArrayList<E>list2 = new ArrayList<>();
    list2.add((E)s1);
    list2.add((E)s2);
    list2.add((E)s3);
    list2.add((E)s8);
    list2.add((E)s9);
    ArrayList<Boolean>list3 = new ArrayList<>();
    list3.add(true);
    list3.add(false);
    return t.checkExpect(greaterThan(list,(Comparator<E>) sc,(E)"").size(),3)
    && t.checkExpect(greaterThan(list1, (Comparator<E>) sc,(E)""), null)
    && t.checkExpect(greaterThan(list2, (Comparator<E>)slc,(E)s1).size(), 1);
  }
  boolean testinOrderList(Tester t)
  {
      ArrayList<E>list = new ArrayList<>();
      list.add((E)s1);
      list.add((E)s2);
      list.add((E)s3);
      ArrayList<E>list1 = new ArrayList<>();
      list1.add(null);
      ArrayList<E> list2 = new ArrayList<>();
      list2.add((E)p1);
      list2.add((E)p3);
      list2.add((E)p4);
      return t.checkExpect(inOrder(list,(Comparator<E>) sc),false)
      &&t.checkException(new IllegalArgumentException("null value in list"),this,"inOrder",list1,sc)&&
      t.checkExpect(inOrder(list2, (Comparator<E>)pc),true)&&t.checkExpect(inOrder(list, (Comparator<E>)slc), true);
  } 
  boolean testinOrderArray(Tester t)
  {
    String[] str = {s1,s2,s3};
    Point[] pot = {p1,p3,p4};
    String[] str2 = {null};
    return t.checkExpect(inOrder((E[])str, (Comparator<E>)sc),false)
    &&t.checkException(new IllegalArgumentException("null value in array"),this,"inOrder",(E[])str2,(Comparator<E>)slc)
    &&t.checkExpect(inOrder((E[])pot,(Comparator<E>)pdc), true)
    &&t.checkExpect(inOrder((E[])pot, (Comparator<E>)pc), true);
  }
  boolean testMergeList(Tester t){
    ArrayList<E>list = new ArrayList<>();
    list.add((E)s1);
    list.add((E)s2);
    list.add((E)s3);
    ArrayList<E>list1 = new ArrayList<>();
    list1.add(null);
    ArrayList<E> list2 = new ArrayList<>();
    list2.add((E)p1);
    list2.add((E)p3);
    list2.add((E)p4);
    ArrayList<E>list3 = new ArrayList<>();
    return t.checkException(new IllegalArgumentException("null value in second list"), this, "merge",(Comparator<E>)sc, list,list1)&&t.checkExpect(merge((Comparator<E>)sc, list3, list).size(), 3)&&t.checkExpect(merge((Comparator<E>)pc, list2, list3).size(), 3);
  }
  
  public static void main(String[]args)
  {
      ArrayList l1 = new ArrayList<>();
      l1.add(null);
      StringCompare sc = new StringCompare();
      inOrder(l1, sc);
  }
}

