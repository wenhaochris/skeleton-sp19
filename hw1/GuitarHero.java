import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {
    public static void main(String[] args) {
     double f = 440.0;
     String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
     GuitarString[] gs = new GuitarString[37];
     for(int i = 0; i < 37; i++){
         double freq = f * Math.pow(2,(i - 24) / 12);
         gs[i] = new GuitarString(freq);
     }

     GuitarString string = gs[0];
     while (true){
         if(StdDraw.hasNextKeyTyped()){
             char key = StdDraw.nextKeyTyped();
             int index = keyboard.indexOf(key);

             if(index != -1){
                 string = gs[index];
                 string.pluck();
             }
         }
         double sample = string.sample();

         StdAudio.play(sample);
         string.tic();;
     }


    }
}
