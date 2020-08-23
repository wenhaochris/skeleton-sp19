public class Body {


    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double g = 6.67e-11;


    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double  calcDistance(Body b){
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;

        double r = Math.sqrt(Math.pow(dx,2) + Math.pow(dy, 2));
        return r;
    }

    public double calcForceExertedBy(Body b){
        double r = this.calcDistance(b);
        return this.mass * b.mass * Body.g / Math.pow(r, 2);
    }

    public double calcForceExertedByX(Body b){
        double r = this.calcDistance(b);
        double f = this.calcForceExertedBy(b);
        double dx = b.xxPos - this.xxPos;
        return f*dx/r;
    }

    public double calcForceExertedByY(Body b){
        double r = this.calcDistance(b);
        double f = this.calcForceExertedBy(b);
        double dy = b.yyPos - this.yyPos;
        return f*dy/r;
    }

    public double calcNetForceExertedByX(Body[] bodies){
        double netForceX = 0;
        for(Body body : bodies){
            if(!this.equals(body)){
                netForceX += this.calcForceExertedByX(body);
            }
        }
        return  netForceX;
    }

    public double calcNetForceExertedByY(Body[] bodies){
        double netForceY = 0;
        for(Body body : bodies){
            if(!this.equals(body)){
                netForceY += this.calcForceExertedByY(body);
            }
        }
        return  netForceY;
    }


    public void update(double dt, double xf, double yf){
           double m = this.mass;
           double xa = xf / m;
           double ya = yf / m;
           this.xxVel += dt * xa;
           this.yyVel += dt * ya;
           this.xxPos += this.xxVel * dt;
           this.yyPos += this.yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos,"images/" + this.imgFileName);
    }



}
