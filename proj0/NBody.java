
public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String fileName){
        In in = new In(fileName);
        int num = in.readInt();
        double radius = in.readDouble();
        Body[] planets = new Body[num];
        for(int i = 0; i < num ; i++){
            planets[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),in.readString());
        }
        return  planets;
    }

    public static void main(String[] args){
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];

        Double u_radius = NBody.readRadius(filename);
        Body[] planets = NBody.readBodies(filename);

        StdDraw.setScale(-u_radius, u_radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");

        for(Body planet : planets){
            planet.draw();
        }

        StdDraw.enableDoubleBuffering();

        int t = 0;
        while(t <= T){
            double[] xForce = new double[planets.length];
            double[] yForce = new double[planets.length];

            for(int i = 0; i < planets.length; i++ ){
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for(int i = 0; i < planets.length; i++){
                planets[i].update(dt, xForce[i], yForce[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Body planet : planets){
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
            StdOut.printf("%d\n", planets.length);
            StdOut.printf("%.2e\n", u_radius);
            for (int i = 0; i < planets.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
            }
        }





    }

}
