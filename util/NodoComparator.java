package util;

import java.util.Comparator;

import tree.BinaryTreeNode;

public class NodoComparator implements Comparator<BinaryTreeNode>{
             
            // Overriding compare()method of Comparator 
                        // for descending order of cgpa
            public int compare(BinaryTreeNode s1, BinaryTreeNode s2) {
                if (s1.getInfo_Freq() < s2.getInfo_Freq())
                    return -1;
                else if (s1.getInfo_Freq() > s2.getInfo_Freq())
                    return 1;

                return 0;
                }
        }

