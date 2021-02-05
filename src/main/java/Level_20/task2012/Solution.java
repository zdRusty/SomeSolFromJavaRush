package Level_20.task2012;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/*
OutputToConsole
*/
public class Solution {
    public static String greeting = "Hello world";

    /**
     * OutputToConsole is an inner base class for improving your attentiveness.
     * An OutputToConsole object encapsulates the information needed
     * for displaying the [greeting] variable to the console.
     *
     * @author JavaRush
     */
    public static class OutputToConsole implements Externalizable {
        private int counter;

        /**
         * @param out A stream for externalization
         * @throws IOException
         */
        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(counter);
        }

        /**
         * @param in A stream for de-externalization
         * @throws IOException
         * @throws ClassNotFoundException
         */
        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            counter = in.readInt();
        }

        /**
         * Class constructor that sets the private counter field.
         */
        public OutputToConsole(int counter) {
            this.counter = counter;
        }

        public OutputToConsole(){
        }

        /**
         * Prints greeting message to console counter times.
         */
        public void printMessage() {
            for (int i = 0; i < counter; i++) {
                System.out.println(greeting);
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // код для проверки
        /*
        OutputToConsole outCons = new OutputToConsole(5);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        br.close();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        outCons.writeExternal(out);
        out.close();
        OutputToConsole loadOutCons = new OutputToConsole();
        loadOutCons.readExternal(in);
        in.close();
        loadOutCons.printMessage();
        */
    }
}
