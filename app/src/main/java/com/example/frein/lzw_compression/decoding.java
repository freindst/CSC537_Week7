package com.example.frein.lzw_compression;

/**
 * Created by frein on 12/11/2015.
 */
import java.util.LinkedList;

public class decoding
{
    private LinkedList<String> curSequence = new LinkedList<String>();
    private LinkedList<Integer> code = new LinkedList<Integer>();
    private LinkedList<String> bits = new LinkedList<String>();

    public decoding()
    {
        String init = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < init.length(); i++)
        {
            this.curSequence.add(init.charAt(i) + "");
            this.code.add(i);
            String theBits = Integer.toBinaryString(i);
            theBits = new String(new char[(5 - theBits.length())]).replace("\0", "0") + theBits;
            this.bits.add(theBits);
        }
    }

    public String output(String t)
    {
        boolean check = false;
        String result = "";
        String full = "";
        String outputSequence = "";
        String conjecture = "";
        int index = 0;
        do
        {
            outputSequence = getSequence(t, index);
            index = index + ((this.code.size() < 32) ? 5: 6);
            full = conjecture + outputSequence.substring(0, 1);
            if (isSequenceExist(full) == -1)
            {
                this.curSequence.add(full);
                this.code.add(this.code.size());
                this.bits.add(Integer.toBinaryString(this.bits.size()));
            }
            conjecture = outputSequence;
            result += outputSequence;
            if (!getSequence(t, index).equals("#"))
            {
                check = true;
            }
            else
            {
                check = false;
            }
        } while(check);
        result += "#";
        return result;
    }

    private String getSequence(String input, int index)
    {
        int stringLength = (this.code.size() < 32) ? 5: 6;
        String theBits = input.substring(index, index + stringLength);
        int code = Integer.parseInt(theBits, 2);
        String curSequence = this.curSequence.get(code);
        return curSequence;
    }

    private int isSequenceExist(String s)
    {
        for (int i = 0; i < this.curSequence.size(); i++)
        {
            if (s.equals(this.curSequence.get(i)))
            {
                return i;
            }
        }
        return -1;
    }
}
