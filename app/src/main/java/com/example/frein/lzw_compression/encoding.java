package com.example.frein.lzw_compression;

/**
 * Created by frein on 12/11/2015.
 */
import java.util.LinkedList;

public class encoding
{
    private LinkedList<String> curSequence = new LinkedList<String>();
    private LinkedList<Integer> code = new LinkedList<Integer>();
    private LinkedList<String> bits = new LinkedList<String>();

    public encoding()
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
        int index = 0;
        String current = getCurrent(t, index);
        char nextChar = t.charAt(index + current.length());
        String extString;

        String result = "";

        while (nextChar != '#')
        {
            String theBits = getBits(current);
            result += theBits;
            extString = current + nextChar;
            this.curSequence.add(extString);
            this.code.add(code.size());
            this.bits.add(Integer.toBinaryString(bits.size()));
            index = index + current.length();
            current = getCurrent(t, index);
            nextChar = t.charAt(index + current.length());
        }
        result += getBits(current) + getBits("#");
        return result;
    }

    //find next longest string that is in curSequence
    private String getCurrent(String t, int index)
    {
        String curString = t.charAt(index) + "";
        String extString;
        boolean check = true;
        while (check)
        {
            boolean isIn = false;
            extString = curString + t.charAt(index + 1);
            for (int i = 0; i < this.curSequence.size(); i++)
            {
                if (extString.equals(this.curSequence.get(i)))
                {
                    isIn = true;
                }
            }
            if (isIn)
            {
                check = true;
                curString = extString;
                index++;
            }
            else
            {
                check = false;
            }
        }
        return  curString;
    }

    private String getBits(String s)
    {
        for (int i = 0; i < this.curSequence.size(); i++)
        {
            if (s.equals(this.curSequence.get(i)))
            {
                String theBits = this.bits.get(i);
                if (code.size() >= 33)
                {
                    theBits = new String(new char[(6 - theBits.length())]).replace("\0", "0") + theBits;
                }
                return theBits;
            }
        }
        return "not found";
    }
}
