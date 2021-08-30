package org.foo.bar;

/**
 * Write a code to Convert sorted array to balanced binary search tree.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

class TreeInorder {
  public List<Integer> list = new ArrayList<>();

  public void inOrder(TreeNode node) {
    if (node == null) {
      return;
    }
    inOrder(node.left);
    list.add(node.val);
    inOrder(node.right);
  }
}

public class Aug30 {
  public static void main(String[] args) {
    List<Integer>[] params = new List[] { //
        Arrays.asList(-10, -3, 0, 5, 9), //
        Arrays.asList(-8, -1, 2, 7, 13), //
        Arrays.asList(1, 2, 3, 4, 5), //
    };
    for (List<Integer> p : params) {
      TreeNode node = sortedArrayToBST(p);
      TreeInorder inorder = new TreeInorder();
      inorder.inOrder(node);
      System.out.println(inorder.list.equals(p));
    }
  }

  public static TreeNode sortedArrayToBST(List<Integer> arr) {
    if (arr.isEmpty()) {
      return null;
    }
    return dfs(arr, 0, arr.size() - 1);
  }

  public static TreeNode dfs(List<Integer> arr, int l, int r) {
    if (l > r) {
      return null;
    }
    int m = l + ((r - l) >> 1);
    TreeNode node = new TreeNode(arr.get(m));
    node.left = dfs(arr, l, m - 1);
    node.right = dfs(arr, m + 1, r);
    return node;
  }
}
