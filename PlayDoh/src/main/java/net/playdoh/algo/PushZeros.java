package net.playdoh.algo;

import java.util.Arrays;

//TC - Linear - o(n)
//SC - Linear - o(n)
//Purpose - Push Zeros to the End
public class PushZeros {

    public static void main(String[] args) {

        System.out.println("Input Array ::: " + args.length + " ::: " + Arrays.toString(args));
        int length = args.length - 1;
        for (int i = 0; i<length; i++) {
            if (Integer.parseInt(args[i].trim()) == 0) {

                String var = args[length];
                args[i] = var;
                args[length] = "0";
                length--;
            }
        }
        System.out.println("Output Array ::: " + Arrays.toString(args));
    }
}
