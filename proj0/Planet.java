public class Planet {
	//Its current x position
	public double xxPos;
	//Its current y position
	public double yyPos;
	//Its current velocity in the x direction
	public double xxVel;
	//Its current velocity in the y direction
	public double yyVel;
	//Its mass
	public double mass;
	//The name of the file that corresponds to the image that depicts the planet
	public String imgFileName;
	//G
	private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
    	          double yV, double m, String img) {
    	xxPos = xP;
    	yyPos = yP;
    	xxVel = xV;
    	yyVel = yV;
    	mass = m;
    	imgFileName = img;
    }

    public Planet(Planet p) {
    	this.xxPos = p.xxPos;
    	this.yyPos = p.yyPos;
    	this.xxVel = p.xxVel;
    	this.yyVel = p.yyVel;
    	this.mass = p.mass;
    	this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
    	double distanceX = this.xxPos - p.xxPos;
    	double distanceY = this.yyPos - p.yyPos;
    	double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    	return distance;
    }

    public double calcForceExertedBy(Planet p) {
    	double r = calcDistance(p);
    	double force = G * this.mass * p.mass / (r * r);
    	return force;
    }

    public double calcForceExertedByX(Planet p) {
    	double distanceX = p.xxPos - this.xxPos;
    	double r = calcDistance(p);
    	double force = calcForceExertedBy(p);
    	double forceX = force * distanceX / r;
    	return forceX;
    }

    public double calcForceExertedByY(Planet p) {
    	double distanceY = p.yyPos - this.yyPos;
    	double r = calcDistance(p);
    	double force = calcForceExertedBy(p);
    	double forceY = force * distanceY / r;
    	return forceY; 
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
    	double netForceX = 0.0;
    	for (Planet p : allPlanets) {
    		if (this.equals(p)) {
    			continue;
    		}
    		else {
    			netForceX += calcForceExertedByX(p);
    		}
    	}
    	return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
    	double netForceY = 0.0;
    	for (Planet p : allPlanets) {
    		if (this.equals(p)) {
    			continue;
    		}
    		else {
    			netForceY += calcForceExertedByY(p);
    		}
    	}
    	return netForceY;
    }

    public void update(double dt, double fX, double fY) {
    	double aX = fX / this.mass;
    	double aY = fY / this.mass;

    	this.xxVel += aX * dt;
    	this.yyVel += aY * dt;

    	this.xxPos += xxVel * dt;
    	this.yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}