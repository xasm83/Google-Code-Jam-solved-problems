//https://leetcode.com/problems/sum-root-to-leaf-numbers/
var sumNumbers = function(root) {
    if (root===null){
        return 0;
    }

    let queue = [root];
    let numbersQueue = [0];
    let totalSum=0;

    while ( queue.length>0){
        let tempNode = queue.shift();
        let tempNumber =numbersQueue.shift()*10;

        if (tempNode.right===null && tempNode.left===null){
            totalSum +=tempNode.val+tempNumber;
        }
        if (  tempNode.left!==null){
            queue.push(tempNode.left);
            numbersQueue.push(tempNumber+tempNode.val) ;
        }
        if (  tempNode.right!==null){
            queue.push(tempNode.right);
            numbersQueue.push(tempNumber+tempNode.val) ;
        }
    }
    return totalSum;
};