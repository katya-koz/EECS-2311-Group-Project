package backend.poster;

import java.util.*;

public class Poster implements Comparable<Poster> {
private int score; 
private String displayName;
private int uniqueId;



public int getScore() {
	return score;
}
public  Poster(int score, String displayName, int uniqueId) {
	//super(); //why did we add super here?
	this.score = score;
	this.displayName = displayName;
	this.uniqueId = uniqueId;
}
public void setScore(int score) {
	this.score = score;
}
public String getDisplayName() {
	return displayName;
}
public void setDisplayName(String displayName) {
	this.displayName = displayName;
}
public int getUniqueId() {
	return uniqueId;
}
public void setUniqueId(int uniqueId) {
	this.uniqueId = uniqueId;
}
@Override
public String toString() {
	return "Poster [score=" + score + ", displayName=" + displayName + ", uniqueId=" + uniqueId + "]";
}
@Override
public int compareTo(Poster o) {
	if (Integer.compare(this.uniqueId,o.uniqueId) != 0) {
        return Integer.compare(this.uniqueId,o.uniqueId);
    }
    else if (this.displayName.compareTo(o.displayName)!=0) {
      //compare name if id is equal
        return this.displayName.compareTo(o.displayName);
    }
    else //compare score if both id and name are equal
    	return Integer.compare(this.score, o.score);
}


}