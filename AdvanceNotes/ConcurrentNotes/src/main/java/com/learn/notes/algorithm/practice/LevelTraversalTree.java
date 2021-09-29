package com.learn.notes.algorithm.practice;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.ArrayList;

public class LevelTraversalTree {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode();
        TreeNode node2 = new TreeNode();
        
        node1.setVal(1);
        node2.setVal(2);
        node1.setLeft(node2);

        LevelTraversalTree solution = new LevelTraversalTree();
        ArrayList<ArrayList<Integer>> result = solution.levelOrder(node1);
        System.out.println(JSON.toJSONString(result));
    }
    
    /**
     *
     * @param root TreeNode类
     * @return int整型ArrayList<ArrayList<>>
     */
    public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
        ArrayList<ArrayList<Integer>> resultlist = new ArrayList<ArrayList<Integer>>();
        function(root, resultlist, 0);
        return resultlist;
    }

    public void function(TreeNode root, ArrayList<ArrayList<Integer>> resultlist,  int index){
        if(root != null && !"#".equals(root.val)){
            ArrayList<Integer> temList = null;
            if(resultlist.size() <= index){
                temList = new ArrayList();
                resultlist.add(temList);
            } else {
                temList = resultlist.get(index);
            }
            temList.add(root.val);
            index = index + 1;
            function(root.left, resultlist, index);
            function(root.right, resultlist, index);
        }
    }
}

@Data
class TreeNode {
   int val = 0;
   TreeNode left = null;
   TreeNode right = null;
}