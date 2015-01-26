public class Planet {
	public  double x;
	public  double y;
	public  double xVelocity;
	public  double yVelocity;
	public double mass;
	public String img;
	public double xNetForce;
	public double yNetForce;
	public double xAccel;
	public double yAccel;
	public double xPosition;
	public double yPosition;

	public Planet(double x, double y, double xVelocity, double yVelocity, double mass, String img) {
		this.x=x;
		this.y=y;
		this.xVelocity=xVelocity;
		this.yVelocity=yVelocity;
		this.mass=mass;
		this.img=img;
	}
	public double calcDistance(Planet p2) {
		 double r=  Math.sqrt((Math.pow((p2.x-x),2)) + (Math.pow((p2.y-y),2))); 
		 return r;
	}

	public double calcPairwiseForce(Planet p2) {
		double f= ((6.67*Math.pow(10,-11))*mass*p2.mass)/((Math.pow((x-p2.x),2)) + (Math.pow((y-p2.y),2)));
		return f;
	}

	public double calcPairwiseForceX(Planet p2){
		double a= (calcPairwiseForce(p2) * (p2.x-x))/ calcDistance(p2);
		return a;
	}

	public double calcPairwiseForceY(Planet p2){
		double b= (calcPairwiseForce(p2) * (p2.y-y))/ calcDistance(p2);
		return b;
	}

	public void setNetForce(Planet[] args){
		xNetForce=0;
		yNetForce=0;
		int counter=0;
		while (counter<args.length){
			if (this==args[counter]){
				counter=counter+1;
			}
			else{
			xNetForce=xNetForce + calcPairwiseForceX(args[counter]);
			yNetForce=yNetForce + calcPairwiseForceY(args[counter]);
			counter=counter+1;
		}
		}
	}

	public void draw(){
		StdDraw.picture(x, y, img);
	}

	public void update(double dt){
		xAccel=xNetForce/mass;
		yAccel=yNetForce/mass;
		xVelocity=xVelocity+dt*xAccel;
		yVelocity=yVelocity+dt*yAccel;
		x=x+dt*xVelocity;
		y=y+dt*yVelocity;
	}
}
