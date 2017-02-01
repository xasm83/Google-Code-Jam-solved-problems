/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} k
 * @return {number}
 */
//https://leetcode.com/problems/kth-smallest-element-in-a-bst/
// could be implemented as rank using local node rank

var kthSmallest = function (root, k) {
    if (root.left === null && root.right === null) {
        return 1;
    }
    let tempNode = root;
    let stack = [];
    while (tempNode.left !== null) {
        stack.push(tempNode);
        tempNode = tempNode.left;
    }

    let nodeNum = 0;
    while (stack.length > 0 || tempNode !== undefined) {
        if (tempNode !== null) {
            nodeNum++;
            if (nodeNum == k) {
                return tempNode.val;
            }
        }

        if (tempNode !== null && tempNode.right !== null) {
            tempNode = tempNode.right;
        } else {
            tempNode = stack.pop();
            nodeNum++;
            if (nodeNum == k) {
                return tempNode.val;
            }
            tempNode = tempNode.right;
        }

        while (tempNode !== null && tempNode.left !== null) {
            stack.push(tempNode);
            tempNode = tempNode.left;
        }
    }
};