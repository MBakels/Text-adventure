package game;

import java.lang.Math;

public class Vector2 {
	// ####################################################
	// properties
	// ####################################################
	public double x;
	public double y;
	
	// ####################################################
	// constructor
	// ####################################################
	public Vector2(double xx, double yy) {
		this.x = xx;
		this.y = yy;
	}
	
	// ####################################################
	// methods
	// ####################################################
	// creators (setters)
	public Vector2 setTo(double xx, double yy) {
		this.x = xx;
		this.y = yy;
		return this;
	}

	public Vector2 create(Vector2 begin, Vector2 end) {
		this.x = end.x - begin.x;
		this.y = end.y - begin.y;
		return this;
	}

	public Vector2 fromAngle(double angle) {
		this.setTo(Math.cos(angle), Math.sin(angle));
		return this;
	}

	public Vector2 copy() {
		return new Vector2(this.x, this.y);
	}
	
	// info (getters)
	public double mag() {
		double a = magSq();
		if(a > 0){
			return Math.sqrt(a);
		}else{
			return 0.0000000001;
		}
		//return Math.sqrt( magSq() );
	}

	public double magSq() {
		return (x*x) + (y*y);
	}

	public double dist(Vector2 other) {
		double dx = this.x - other.x;
		double dy = this.y - other.y;
		return Math.sqrt( (dx*dx) + (dy*dy) );
	}

	public Vector2 getNormalized() {
		return new Vector2( this.x/mag(), this.y/mag() );
	}

	public Vector2 normalize() {
		double m = mag();
		this.x /= m;
		this.y /= m;
		return this;
	}
	
	public Vector2 limit(double max) {
		if (mag() > max) {
			this.normalize();
			this.multS(max);
		}
		return this;
	}

	public Vector2 setMag(double magnitude) {
		this.normalize();
		this.multS(magnitude);
		return this;
	}

	public double getAngle() {
		return Math.atan2(y,x);
	}

	public double heading() {
		return getAngle();
	}

	public double getAngleToVector(Vector2 other) {
		double dot = this.dot(other);
		double l1 = this.mag();
		double l2 = other.mag();
		return Math.acos(dot/(l1*l2));
	}

	public double dot(Vector2 other) {
		return (this.x * other.x) + (this.y * other.y);
	}
	
	// manipulators
	public Vector2 rotate(double angle) {
		double currAngle = this.getAngle();
		currAngle += angle;
		double xx = Math.cos(currAngle);
		double yy = Math.sin(currAngle);
		double currLength = this.mag();
		this.x = xx * currLength;
		this.y = yy * currLength;
		return this;
	}

	public Vector2 add(Vector2 other) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}

	public Vector2 sub(Vector2 other) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}

	public Vector2 mult(Vector2 other) {
		this.x *= other.x;
		this.y *= other.y;
		return this;
	}

	public Vector2 multS(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
		return this;
	}
	
	public Vector2 div(Vector2 other) {
		this.x /= other.x;
		this.y /= other.y;
		return this;
	}

	public Vector2 divS(double scalar) {
		this.x /= scalar;
		this.y /= scalar;
		return this;
	}

	public static double rad2deg(double a) {
		return a * (180 / Math.PI);
	}

	public static double deg2rad(double a) {
		return a * (Math.PI / 180);
	}

	public String toString() {
		return new String("["+this.x+", "+this.y+"]");
	}
	
	public static Vector2 add(Vector2 v1, Vector2 v2){
		return new Vector2(v1.x + v2.x, v1.y + v2.y);
	}
	
	public static Vector2 sub(Vector2 v1, Vector2 v2){
		return new Vector2(v1.x - v2.x, v1.y - v2.y);
	}
	
	public static Vector2 div(Vector2 v1, Vector2 v2){
		return new Vector2(v1.x / v2.x, v1.y / v2.y);
	}
	
	public static Vector2 divS(Vector2 v1, double scalar){
		return new Vector2(v1.x / scalar, v1.y / scalar);
	}
	
	public static boolean CompareVectors(Vector2 vec1, Vector2 vec2){
		int x1 = (int)vec1.x;
		int y1 = (int)vec1.y;
		int x2 = (int)vec2.x;
		int y2 = (int)vec2.y;
		
		if (x1 != x2 || y1 != y2)return false;
		return true;
	}

}
