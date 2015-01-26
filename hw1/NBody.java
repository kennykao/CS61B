public class NBody {
	public static void main(String[] args){
		double t= Double.parseDouble(args[0]);
		double dt= Double.parseDouble(args[1]);
		String filename=args[2];
		In in= new In(filename);
		int n= in.readInt();
		double y= in.readDouble();

		Planet[] planetArray= new Planet[n];
		int i=0;
		while (i<=n-1){
			planetArray[i]=getPlanet(in);
			i=i+1;
		}

		StdDraw.setScale(-y, y);
		StdDraw.picture(0,0,"/images/starfield.jpg");

		int q=0;
		while (q<=n-1) {
			StdDraw.picture(planetArray[q].x,planetArray[q].y, "/images/" + planetArray[q].img);
			q=q+1;
		}

		double time=0;
		while (time< t){
			int b=0;
			while (b<=n-1) {
				planetArray[b].setNetForce(planetArray);
				b=b+1;
			}

			int c=0;
			while (c<=n-1){
				planetArray[c].update(dt);
				c=c+1;
			}

			StdDraw.picture(0,0,"/images/starfield.jpg");

			int w=0;
			while (w<=n-1){
				StdDraw.picture(planetArray[w].x,planetArray[w].y,"/images/"+planetArray[w].img);
				w=w+1;
			}
			StdDraw.show(10);

			time=time+dt;
		}
		StdOut.printf("%d\n", N);
		StdOut.printf("%.2e\n", R);
		for (int i = 0; i < N; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                   px[i], py[i], vx[i], vy[i], mass[i], image[i]);
    	}
	}
	public static Planet getPlanet(In object){
		double x= object.readDouble();
		double y= object.readDouble();
		double xVelocity= object.readDouble();
		double yVelocity= object.readDouble();
		double mass= object.readDouble();
		String img=object.readString();
		Planet nextPlanet=new Planet(x,y,xVelocity,yVelocity,mass,img);
		return nextPlanet;
	}
}
