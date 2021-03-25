public class NBody {
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		@Deprecated
		int number = in.readInt();
		@Deprecated
		double radius = in.readDouble();

		return radius;
	}

	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		@Deprecated
		int number = in.readInt();
		@Deprecated
		double radius = in.readDouble();

		Planet[] allPlanets = new Planet[number];
		for (int i = 0; i < number; i++) {
			@Deprecated
			double xxPos = in.readDouble();
			@Deprecated
			double yyPos = in.readDouble();
			@Deprecated
			double xxVel = in.readDouble();
			@Deprecated
			double yyVel = in.readDouble();
			@Deprecated
			double mass = in.readDouble();
			@Deprecated
			String imgFileName = in.readString();
			allPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return allPlanets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] allPlanets = readPlanets(filename);
		double radius = readRadius(filename);

		StdDraw.enableDoubleBuffering();

		double time = 0;
		while (time < T) {
			double[] xForces = new double[allPlanets.length];
			double[] yForces = new double[allPlanets.length];
			int i = 0;
			for (Planet p : allPlanets) {
				xForces[i] = p.calcNetForceExertedByX(allPlanets);
				yForces[i] = p.calcNetForceExertedByY(allPlanets);
				i++;
			}

			for (int j = 0; j < allPlanets.length; j++) {
				allPlanets[j].update(dt, xForces[j], yForces[j]);
			}

			StdDraw.setScale(-radius, radius);
		
		    StdDraw.clear();

		    StdDraw.picture(0, 0, "images/starfield.jpg");

		    for (Planet p : allPlanets) {
		    	p.draw();
		    }

		    StdDraw.show();

		    StdDraw.pause(10);

		    time += dt;
		}

		StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
        	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                          allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
        }
	}
}